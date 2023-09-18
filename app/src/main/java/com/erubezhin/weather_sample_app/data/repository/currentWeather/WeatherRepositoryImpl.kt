package com.erubezhin.weather_sample_app.data.repository.currentWeather

import android.content.Context
import com.erubezhin.weather_sample_app.core.platfrom.BaseRepository
import com.erubezhin.weather_sample_app.core.platfrom.NetworkHandler
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import java.lang.Exception

/**
 * Implementation of the [WeatherRepository].
 *
 * @param context resources of the application.
 */
class WeatherRepositoryImpl(context: Context) : BaseRepository(), WeatherRepository {
    private val networkHandler = NetworkHandler(context)
    private val service = WeatherApiService()

    override fun getTodayWeather(
        lat: Double, lon: Double
    ): Response<TodayResponse> = if (networkHandler.isNetworkAvailable()) {
        request(service.getTodayWeather(lat, lon))
    } else {
        Response.Error(Exception("Internet not found"))
    }
}