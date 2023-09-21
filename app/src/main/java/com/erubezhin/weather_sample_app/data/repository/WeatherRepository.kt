package com.erubezhin.weather_sample_app.data.repository

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse

/** Repository that helps to load weather information from the server side. */
interface WeatherRepository {
    /**
     * Load today weather data by provided latitude[lat] and longitude[lon].
     *
     * @param lat latitude of the user location.
     * @param lon longitude of the user location.
     */
    fun getTodayWeather(
        lat: Double,
        lon: Double,
    ): Response<TodayResponse>

    /**
     * Load hourly weather data by provided latitude[lat] and longitude[lon].
     *
     * @param lat latitude of the user location.
     * @param lon longitude of the user location.
     */
    fun getHourlyWeather(
        lat: Double,
        lon: Double,
    ): Response<HourlyResponse>
}
