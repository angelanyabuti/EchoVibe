package com.example.echovibe.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Step 1: Custom Gradients
data class AppGradients(
    val primaryGradient: Brush,
    val backgroundGradient: Brush
)

// Step 2: Local Provider
val LocalAppGradients = staticCompositionLocalOf<AppGradients> {
    error("No gradients provided")
}

// Step 3: Color Schemes
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

// Step 4: Final Theme with Gradient Support
@Composable
fun EchoVibeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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

    val gradients = AppGradients(
        primaryGradient = Brush.horizontalGradient(colors = listOf(Black2, Blue1)),
        backgroundGradient = Brush.verticalGradient(colors = listOf(Black1, Blue1))
    )

    CompositionLocalProvider(LocalAppGradients provides gradients) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
