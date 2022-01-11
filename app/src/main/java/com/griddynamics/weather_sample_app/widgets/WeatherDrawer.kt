package com.griddynamics.weather_sample_app.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDrawer() {
    Column() {

    }
}

val weatherDrawerShape = object : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                200.dp.value,
                0f,
                size.width,  /* width */
                size.height, /* height */
            )
        )
    }
}