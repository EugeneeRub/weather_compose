package com.griddynamics.weather_sample_app.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

sealed class BaseThemeColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val textPrimary: Color,
    val textSecondary: Color,
)

object LightColors : BaseThemeColors(
    primary = Color(0xFF4DBBD8),
    secondary = Color(0xFF70c9e2),
    background = Color(0xFFFFFFFF),
    textPrimary = Color(0XFF2E2E2E),
    textSecondary = Color(0XFFEDEDED),
)

object DarkColors : BaseThemeColors(
    primary = Color(0xFF4DBBD8),
    secondary = Color(0xFF70c9e2),
    background = Color(0XFF3B444B),
    textPrimary = Color(0XFFE3E3E3),
    textSecondary = Color(0XFFEDEDED),
)

val Colors.textPrimary: Color
    get() = if (isLight) LightColors.textPrimary else DarkColors.textPrimary

val Colors.textSecondary: Color
    get() = if (isLight) LightColors.textSecondary else DarkColors.textSecondary