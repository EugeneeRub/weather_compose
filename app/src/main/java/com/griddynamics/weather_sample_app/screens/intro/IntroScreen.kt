package com.griddynamics.weather_sample_app.screens.intro

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme

@Composable
fun IntroScreen() {
    WeatherComposeTheme {

    }
}

@Preview(
    showBackground = true,
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun IntroPreview() {
    IntroScreen()
}