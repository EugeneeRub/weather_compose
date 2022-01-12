package com.griddynamics.weather_sample_app.utils

import androidx.compose.ui.graphics.Color
import java.util.*

data class SplashBackground(
    val wave1Color: Color,
    val wave2Color: Color,
    val textColor: Color
)

val seasonalMainData: SplashBackground = when (Calendar.getInstance()[Calendar.MONTH]) {
    in 1..2, 12 -> SplashBackground(Color(0XFF1BB3D2), Color(0XFF71C3DB), Color(0XFF1BB3D2))
    in 3..5 -> SplashBackground(Color(0XFFACBE7C), Color(0XFFCCDDAD), Color(0XFFACBE7C))
    in 6..8 -> SplashBackground(Color(0XFFD28F33), Color(0XFFFFEC00), Color(0XFFD28F33))
    in 9..11 -> SplashBackground(Color(0xFFB15246), Color(0XFFD28F33), Color(0xFFB15246))
    else -> SplashBackground(Color(0XFF1BB3D2), Color(0XFF71C3DB), Color(0XFF1BB3D2))
}
