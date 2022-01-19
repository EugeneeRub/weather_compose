package com.griddynamics.weather_sample_app.data.repository.currentWeather

import com.griddynamics.weather_sample_app.data.model.response.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val GEO_WEATHER = "weather"
    }

    @GET(GEO_WEATHER)
    @Headers("Content-Type: application/json")
    fun getGeoDataByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<CurrentWeatherResponse>
}