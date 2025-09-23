package com.example.echovibe.pages

import android.os.Looper.prepare
import androidx.annotation.OptIn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.echovibe.R
import kotlinx.coroutines.delay

/*
* This is the Now Playing Screen
* Created using the media3 Exoplayer
* */
@Composable
fun NowPlayingScreen(navController: NavHostController, trackName: String) {

    val context = LocalContext.current

    //Creating the Exoplayer
    val exoPlayer = remember {
        //creating the Exoplayer instance
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3") //preparing the player by adding media items
            setMediaItem(mediaItem) //preparing the player by adding media items
            prepare() //Starts the media loading

        }
    }

    /***
     * Release the player when leaving the screen or closing the app
     * Playback requires resources that might be limited
     * Releasing the player can help prevent the battery from draining and other apps from crashing
     */

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release()  }
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
    val state = rememberPlayPauseButtonState(player)
    val duration = player.duration.coerceAtLeast(0L)
    var sliderPosition by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    //updating the slider position as playback progresses
    LaunchedEffect(player) {
        while (true) {
            if (!isDragging && player.isPlaying) {
                sliderPosition = player.currentPosition.toFloat()
            }
            delay(500) //update every 0.5s
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = {
                /**
                 * Called when the user finished dragging the slider
                 * Seek to a position in the current song
                 * */
                player.seekTo(sliderPosition.toLong()*1000)
            },
            valueRange = 0f..duration.toFloat(),
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = Color.Gray
            )
        )

        // Time labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatTime(sliderPosition.toLong()), color = Color.LightGray)
            Text(text = formatTime(duration), color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Main Playback Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Previous */ }) {
                Icon(Icons.Default.SkipPrevious, contentDescription = "Previous", tint = Color.White, modifier = Modifier.size(40.dp))
            }

            //PlayPauseButtonState
            IconButton(
                onClick = state::onClick, enabled = state.isEnabled,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    imageVector = if (state.showPlay) Icons.Default.PlayArrow else Icons.Default.Pause,
                    contentDescription = "Play", tint = Color.White, modifier = Modifier.size(50.dp))
            }

            IconButton(onClick = { /* TODO: Next */ }) {
                Icon(Icons.Default.SkipNext, contentDescription = "Next", tint = Color.White, modifier = Modifier.size(40.dp))
            }
        }
    }
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

fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)  // e.g. 0:59 → 1:00 → 1:01
}

