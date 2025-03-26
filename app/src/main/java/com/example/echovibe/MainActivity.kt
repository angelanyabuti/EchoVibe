package com.example.echovibe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.echovibe.ui.theme.EchoVibeTheme
import com.example.echovibe.auth.Registration // Import the Registration composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Show XML splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EchoVibeTheme(darkTheme = false, dynamicColor = false) {
                var isSplashAnimationVisible by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(3000) // Show animation for 3 seconds
                    isSplashAnimationVisible = false
                }

                if (isSplashAnimationVisible) {
                    AnimatedSplashTransition()
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedSplashTransition() {
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(1000))
        scale.animateTo(1f, tween(1000, easing = FastOutSlowInEasing))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Just fade in the transition, no need for an extra logo
        Box(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha.value)
                .scale(scale.value)
        )
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Registration()
        }
    }
}
