package com.erubezhin.weather_sample_app.feature.ui.theme

import android.content.res.Resources
import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import java.util.*

sealed class BaseThemeColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val viewSelected: Color,
    val iconTint: Color,
)

object LightColors : BaseThemeColors(
    primary = Color(0xFF4DBBD8),
    secondary = Color(0xFF70c9e2),
    background = Color(0xFFFFFFFF),
    textPrimary = Color(0XFF2E2E2E),
    textSecondary = Color(0xFF3A3A3A),
    viewSelected = Color(0xFFF3F3F3),
    iconTint = Color(0XFF2E2E2E),
)

object DarkColors : BaseThemeColors(
    primary = Color(0xFF4DBBD8),
    secondary = Color(0xFF70c9e2),
    background = Color(0XFF3B444B),
    textPrimary = Color(0XFFE3E3E3),
    textSecondary = Color(0XFFEDEDED),
    viewSelected = Color(0xFF4A5258),
    iconTint = Color(0XFFE3E3E3),
)

sealed class SeasonColors(
    val wavePrimary: Color,
    val waveSecondary: Color,
    val textColor: Color,
) {
    object WinterColors : SeasonColors(
            wavePrimary = Color(0XFF1BB3D2),
            waveSecondary = Color(0XFF71C3DB),
            textColor = Color(0XFF1BB3D2),
        )

    object SpringColors : SeasonColors(
            wavePrimary = Color(0XFFACBE7C),
            waveSecondary = Color(0XFFCCDDAD),
            textColor = Color(0XFFACBE7C),
        )

    object SummerColors : SeasonColors(
            wavePrimary = Color(0xFFB15246),
            waveSecondary = Color(0XFFD28F33),
            textColor = Color(0xFFB15246),
        )

    object AutumnColors : SeasonColors(
            wavePrimary = Color(0XFFD28F33),
            waveSecondary = Color(0XFFFFEC00),
            textColor = Color(0XFFD28F33),
        )

    companion object {
        fun getSeasonColors(calendar: Calendar): SeasonColors {
            return when (calendar[Calendar.MONTH] + 1) {
                in 1..2, 12 -> WinterColors
                in 3..5 -> SpringColors
                in 6..8 -> SummerColors
                in 9..11 -> AutumnColors
                else -> throw Resources.NotFoundException("")
            }
        }
    }
}

val Colors.textPrimary: Color
    get() = if (isLight) LightColors.textPrimary else DarkColors.textPrimary

val Colors.iconTint: Color
    get() = if (isLight) LightColors.iconTint else DarkColors.iconTint

val Colors.textSecondary: Color
    get() = if (isLight) LightColors.textSecondary else DarkColors.textSecondary

val Colors.viewSelected: Color
    get() = if (isLight) LightColors.viewSelected else DarkColors.viewSelected
