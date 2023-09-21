package com.erubezhin.weather_sample_app.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors

@Composable
fun WavesBackground(waveColor: Color) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_wave),
            contentDescription = "Background wave",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(waveColor),
        )
    }
}

@Preview(name = "Wave winter background")
@Composable
fun WavesBackgroundWinterPreview() {
    WavesBackground(SeasonColors.WinterColors.wavePrimary)
}

@Preview(name = "Wave spring background")
@Composable
fun WavesBackgroundSpringPreview() {
    WavesBackground(SeasonColors.SpringColors.wavePrimary)
}

@Preview(name = "Wave summer background")
@Composable
fun WavesBackgroundSummerPreview() {
    WavesBackground(SeasonColors.SummerColors.wavePrimary)
}

@Preview(name = "Wave autumn background")
@Composable
fun WavesBackgroundAutumnPreview() {
    WavesBackground(SeasonColors.AutumnColors.wavePrimary)
}
