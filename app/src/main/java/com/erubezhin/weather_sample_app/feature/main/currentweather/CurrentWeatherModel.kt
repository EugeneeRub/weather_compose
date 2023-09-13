package com.erubezhin.weather_sample_app.feature.main.currentweather

import androidx.annotation.DrawableRes

/**
 * Model that represent current day screen.
 *
 * @property day represent current day;
 * @property temperature represent current temperature;
 * @property city represent current city;
 * @property lastUpdatedTime shows the last updated time;
 * @property weatherIconId drawable resource of the weather state;
 */
data class CurrentWeatherModel(
    val day: String = "",
    val temperature: String = "0°",
    val city: String = "",
    val lastUpdatedTime: String = "",
    @DrawableRes val weatherIconId: Int,
)

