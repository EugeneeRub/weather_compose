package com.erubezhin.weather_sample_app.feature.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkColors.primary,
    secondary = DarkColors.secondary,
    background = DarkColors.background,
)

private val LightColorPalette = lightColors(
    primary = LightColors.primary,
    secondary = LightColors.secondary,
    background = LightColors.background,
)

@Composable
fun WeatherComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}