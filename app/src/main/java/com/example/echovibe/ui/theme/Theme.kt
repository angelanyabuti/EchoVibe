package com.example.echovibe.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Pink80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Black1,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue2,
    secondary = Blue1,
    tertiary = White1,
    background = Black1,
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.Blue,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Black2,
    onSurface = Color(0xFF1C1B1F),

)

@Composable
fun EchoVibeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}