package com.griddynamics.weather_sample_app.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.griddynamics.weather_sample_app.R
import java.util.*

data class SplashBackground(
    @DrawableRes val image: Int,
    val textColor: Color
)

fun getSeasonalSplashData(): SplashBackground = when (Calendar.getInstance()[Calendar.MONTH]) {
    in 1..2, 12 -> SplashBackground(R.drawable.splash_winter, Color(0XFF2E2E2E))
    in 3..5 -> SplashBackground(R.drawable.splash_spring, Color(0XFF2E2E2E))
    in 6..8 -> SplashBackground(R.drawable.splash_summer, Color(0XFF2E2E2E))
    in 9..11 -> SplashBackground(R.drawable.splash_autumn, Color(0XFF2E2E2E))
    else -> SplashBackground(R.drawable.splash_spring, Color(0XFF2E2E2E))
}
