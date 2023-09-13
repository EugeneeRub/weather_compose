package com.erubezhin.weather_sample_app.data.repository.currentWeather

import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/** Weather API of the application. */
interface WeatherApi {

    @GET("weather")
    @Headers("Content-Type: application/json")
    fun getGeoDataByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<CurrentWeatherResponse>

}