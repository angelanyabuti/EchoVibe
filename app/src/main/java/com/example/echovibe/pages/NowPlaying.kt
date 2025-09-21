package com.example.echovibe.pages

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.echovibe.R

@Composable
fun NowPlayingScreen(navController: NavHostController, trackName: String) {
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
        seekbar()

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
            text = "LMFAO",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

//Seekbar
@Composable
fun seekbar() {
    var sliderPosition by remember { mutableStateOf(30f) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..180f,
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
            Text(text = "${sliderPosition.toInt()} sec", color = Color.LightGray)
            Text(text = "3:00", color = Color.LightGray)
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

            IconButton(
                onClick = { /* TODO: Play/Pause */ },
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White, modifier = Modifier.size(50.dp))
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
