package com.erubezhin.weather_sample_app.data.repository

import com.erubezhin.weather_sample_app.core.platfrom.RetrofitBuilder
import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import retrofit2.Call

/** Implementation of the [WeatherApi]. */
class WeatherApiService : WeatherApi {
    private val weatherApi by lazy { RetrofitBuilder.retrofit.create(WeatherApi::class.java) }

    override fun getTodayWeather(
        lat: Double,
        lon: Double,
    ): Call<TodayResponse> = weatherApi.getTodayWeather(lat, lon)

    override fun getHourlyWeather(
        lat: Double,
        lon: Double,
    ): Call<HourlyResponse> = weatherApi.getHourlyWeather(lat, lon)
}
