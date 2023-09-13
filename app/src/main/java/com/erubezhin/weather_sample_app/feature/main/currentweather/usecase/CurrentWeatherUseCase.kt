package com.erubezhin.weather_sample_app.feature.main.currentweather.usecase

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse

interface CurrentWeatherUseCase {

    suspend fun loadCurrentWeather(params: Params): Response<CurrentWeatherResponse>

    data class Params(val lat: Double, val lon: Double)
}