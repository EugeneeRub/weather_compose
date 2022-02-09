package com.griddynamics.weather_sample_app.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.griddynamics.weather_sample_app.R

@Composable
fun WavesBackground(wave1Color: Color, reversed: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_wave_2),
            contentDescription = "Background wave 1",
            modifier = Modifier
                .scale(if (reversed) -1f else 1f, 1f)
                .fillMaxWidth()
                .fillMaxHeight(160f),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(wave1Color)
        )
    }
}