package com.erubezhin.weather_sample_app.feature.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.erubezhin.weather_sample_app.R

val lombokFontFamily = FontFamily(
    Font(R.font.lombok_regular, FontWeight.Normal),
    Font(R.font.lombok_regular, FontWeight.Medium),
    Font(R.font.lombok_regular, FontWeight.Bold)
)

val moonFontFamily = FontFamily(
    Font(R.font.moon_light, FontWeight.Normal),
    Font(R.font.moon_light, FontWeight.Medium),
    Font(R.font.moon_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = moonFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)