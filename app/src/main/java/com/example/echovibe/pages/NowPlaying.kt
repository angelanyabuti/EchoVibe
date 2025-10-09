package com.example.echovibe.pages

import android.os.Looper.prepare
import androidx.annotation.OptIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.compose.ui.geometry.Size
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.echovibe.R
import com.example.echovibe.viewModels.NowPlayingViewModel
import kotlinx.coroutines.delay

/*
* This is the Now Playing Screen
* Created using the media3 Exoplayer
* */
@Composable
fun NowPlayingScreen(navController: NavHostController, trackName: String, viewModel: NowPlayingViewModel = hiltViewModel()) {

    val context = LocalContext.current

    //Creating the Exoplayer
    val exoPlayer = viewModel.exoPlayer

    LaunchedEffect(Unit) {
        viewModel.preloadTrack("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.pause() } // keep it alive, don't release
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Album Art + Track Info
        songCard(trackName = trackName)

        // Progress bar + playback controls
        seekbar(player = exoPlayer)

        // Extra controls (shuffle, repeat, etc.)
        bottomControls()
    }
}

//Track Info Card
@Composable
fun songCard(
    modifier: Modifier = Modifier,
    trackName: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Now Playing",
            color = MaterialTheme.colorScheme.primary
        )
        AsyncImage(
            model = R.drawable.happysong,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .border(
                    width = 6.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = trackName,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 22.sp),
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Leona Lewis",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

//Seekbar
@OptIn(UnstableApi::class)
@Composable
fun seekbar(player: Player) {
    val duration = player.duration.coerceAtLeast(1L)
    var sliderPosition by remember { mutableStateOf(0f) }
    var bufferedPosition by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(player.isPlaying) }

    val primaryColor = MaterialTheme.colorScheme.primary
    val bufferColor = MaterialTheme.colorScheme.secondary

    // Keep UI synced with player
    LaunchedEffect(player) {
        while (true) {
            if (!isDragging) {
                sliderPosition = player.currentPosition.toFloat()
                bufferedPosition = player.bufferedPosition.toFloat()
                isPlaying = player.isPlaying
            }
            delay(500)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🎚️ Seekbar (Buffered + Played + Thumb)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val barHeight = size.height / 3
                val yCenter = size.height / 2

                // Unplayed
                drawRoundRect(
                    color = Color.Gray.copy(alpha = 0.3f),
                    size = Size(width = size.width, height = barHeight),
                    topLeft = Offset(0f, yCenter - barHeight / 2),
                    cornerRadius = CornerRadius(100f)
                )

                // Buffered
                val bufferedWidth = (bufferedPosition / duration) * size.width
                drawRoundRect(
                    color = Color.LightGray.copy(alpha = 0.7f),
                    size = Size(width = bufferedWidth, height = barHeight),
                    topLeft = Offset(0f, yCenter - barHeight / 2),
                    cornerRadius = CornerRadius(100f)
                )

                // Played
                val playedWidth = (sliderPosition / duration) * size.width
                drawRoundRect(
                    color = primaryColor,
                    size = Size(width = playedWidth, height = barHeight),
                    topLeft = Offset(0f, yCenter - barHeight / 2),
                    cornerRadius = CornerRadius(100f)
                )

                // Thumb
                val thumbX = playedWidth.coerceIn(0f, size.width)
                drawCircle(
                    color = bufferColor,
                    radius = if (isDragging) 10.dp.toPx() else 6.dp.toPx(),
                    center = Offset(thumbX, yCenter)
                )
            }

            // Transparent slider overlay (for drag)
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    isDragging = true
                },
                onValueChangeFinished = {
                    player.seekTo(sliderPosition.toLong())
                    isDragging = false
                },
                valueRange = 0f..duration.toFloat(),
                modifier = Modifier.fillMaxSize(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent
                )
            )
        }

        // ⏱️ Time labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatTime(sliderPosition.toLong()), color = Color.LightGray)
            Text(text = formatTime(duration), color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🎵 Playback Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { player.seekBack() }) {
                Icon(Icons.Default.SkipPrevious, contentDescription = "Previous", tint = Color.White, modifier = Modifier.size(40.dp))
            }

            IconButton(
                onClick = {
                    if (player.isPlaying) {
                        player.pause()
                    } else {
                        player.play()
                    }
                    isPlaying = player.isPlaying
                },
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }

            IconButton(onClick = { player.seekForward() }) {
                Icon(Icons.Default.SkipNext, contentDescription = "Next", tint = Color.White, modifier = Modifier.size(40.dp))
            }
        }
    }
}


fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}




//Bottom Control
@Composable
fun bottomControls() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { /* TODO: Shuffle */ }) {
            Icon(Icons.Default.Shuffle, contentDescription = "Shuffle", tint = Color.LightGray)
        }
        IconButton(onClick = { /* TODO: Repeat */ }) {
            Icon(Icons.Default.Repeat, contentDescription = "Repeat", tint = Color.LightGray)
        }
        IconButton(onClick = { /* TODO: Playlist */ }) {
            Icon(Icons.Default.QueueMusic, contentDescription = "Playlist", tint = Color.LightGray)
        }
    }
}



