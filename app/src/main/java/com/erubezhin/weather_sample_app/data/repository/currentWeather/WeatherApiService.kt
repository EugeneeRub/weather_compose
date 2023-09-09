package com.erubezhin.weather_sample_app.data.repository.currentWeather

import com.erubezhin.weather_sample_app.core.platfrom.RetrofitBuilder
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse
import retrofit2.Call

class WeatherApiService : WeatherApi {
    private val weatherApi by lazy { RetrofitBuilder.retrofit.create(WeatherApi::class.java) }

    override fun getGeoDataByCoordinates(
        lat: Double, lon: Double
    ): Call<CurrentWeatherResponse> = weatherApi.getGeoDataByCoordinates(lat, lon)
}