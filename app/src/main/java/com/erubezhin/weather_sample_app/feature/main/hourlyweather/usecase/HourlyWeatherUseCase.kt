package com.erubezhin.weather_sample_app.feature.main.hourlyweather.usecase

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse

/** Helps to connect the [HourlyWeatherViewModel] with the [WeatherRepository]. */
interface HourlyWeatherUseCase {
    /** Load hourly weather data from repository using [params] values. */
    suspend fun loadHourlyWeather(params: Params): Response<HourlyResponse>

    data class Params(val lat: Double, val lon: Double)
}
