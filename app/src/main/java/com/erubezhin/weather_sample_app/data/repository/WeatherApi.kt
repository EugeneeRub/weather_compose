package com.erubezhin.weather_sample_app.data.repository

import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/** Weather API of the application. */
interface WeatherApi {
    @GET("weather")
    @Headers("Content-Type: application/json")
    fun getTodayWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Call<TodayResponse>

    @GET("forecast")
    @Headers("Content-Type: application/json")
    fun getHourlyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Call<HourlyResponse>
}
