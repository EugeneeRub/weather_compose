package com.erubezhin.weather_sample_app.feature.main.todayweather

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.erubezhin.weather_sample_app.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Model that represent current day screen.
 *
 * @property day represent current day;
 * @property temperature represent current temperature;
 * @property tempFeelsLike represent the feels like temperature;
 * @property city represent current city;
 * @property lastUpdatedTime shows the last updated time;
 * @property weatherIconId drawable resource of the weather state;
 */
data class TodayWeatherModel(
    val day: String,
    val temperature: String,
    val tempFeelsLike: String,
    val city: String,
    val lastUpdatedTime: String,
    @DrawableRes val weatherIconId: Int,
    val details: DetailsModel,
) {
    /**
     * Uses [temperature] to return new string.
     *
     * Example: 16 -> 16°;
     */
    fun getTemperatureString() = "$temperature°"

    /**
     * Uses [tempFeelsLike] to return new string.
     *
     * Example: 16 -> 16°;
     */
    fun getTemperatureFeelsLikeString() = "${tempFeelsLike}°"
}

/**
 * Model that represent details of the current temperature.
 *
 * @property tempMin minimum temperature;
 * @property tempMax maximum temperature;
 * @property wind wind of the weather;
 * @property pressure pressure of the environment;
 * @property humidity humidity of the environment;
 * @property sunrise sunrise of the day;
 * @property sunset sunset of the day;
 * @property visibility visibility condition;
 */
data class DetailsModel(
    val tempMin: String,
    val tempMax: String,
    val wind: Int,
    val pressure: Int,
    val humidity: Int,
    val sunrise: Long,
    val sunset: Long,
    val visibility: Visibility,
) {

    /**
     * Combine [tempMin] and [tempMax] to return new string.
     *
     * Example: 16,22 -> 16°/22°;
     */
    fun getMinMaxTemperatureString() = "$tempMin°/$tempMax°"

    /** Returns sunrise time string in HH:mm format. */
    fun getSunriseTime(): String = SimpleDateFormat("HH:mm").format(Date(sunrise))

    /** Returns sunset time string in HH:mm format. */
    fun getSunsetTime(): String = SimpleDateFormat("HH:mm").format(Date(sunset))

    /** Represents types of weather visibility, holds [stringId] to show it in UI. */
    enum class Visibility(@StringRes val stringId: Int) {
        DENSE_FOG(R.string.terminology_dense_fog),
        THICK_FOG(R.string.terminology_thick_fog),
        MODERATE_FOG(R.string.terminology_moderate_fog),
        LIGHT_FOG(R.string.terminology_light_fog),
        VERY_LIGHT_FOG(R.string.terminology_very_light_fog),
        LIGHT_MIST(R.string.terminology_light_mist),
        VERY_LIGHT_MIST(R.string.terminology_very_light_mist),
        CLEAR_AIR(R.string.terminology_clear_air),
        VERY_CLEAR_AIR(R.string.terminology_very_clear_air);

        companion object {
            /** Returns [Visibility] depends on the provided [visibilityValue].  */
            fun getVisibility(visibilityValue: Int) = when (visibilityValue) {
                in 0..50 -> DENSE_FOG
                in 51..200 -> THICK_FOG
                in 201..770 -> MODERATE_FOG
                in 771..1000 -> LIGHT_FOG
                in 1001..2000 -> VERY_LIGHT_FOG
                in 2001..2800 -> LIGHT_MIST
                in 2801..10_000 -> VERY_LIGHT_MIST
                in 10_001..23_000 -> CLEAR_AIR
                else -> VERY_CLEAR_AIR
            }
        }
    }
}

