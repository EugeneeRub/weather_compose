package com.erubezhin.weather_sample_app.feature.main.todayweather.usecase

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse

/** Helps to connect the [TodayWeatherViewModel] with the [WeatherRepository]. */
interface TodayWeatherUseCase {
    /** Load current weather data from repository using [params] values. */
    suspend fun loadCurrentWeather(params: Params): Response<TodayResponse>

    data class Params(val lat: Double, val lon: Double)
}
