package com.example.echovibe.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun NowPlayingScreen(navController: NavHostController, trackName: String) {

    Text(text = "Now Playing: $trackName")
}