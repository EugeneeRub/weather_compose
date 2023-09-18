package com.erubezhin.weather_sample_app.feature.main.todayweather.usecase

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse

interface TodayWeatherUseCase {

    suspend fun loadCurrentWeather(params: Params): Response<TodayResponse>

    data class Params(val lat: Double, val lon: Double)
}