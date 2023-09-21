package com.erubezhin.weather_sample_app.data.repository

import android.content.Context
import android.content.res.Resources.NotFoundException
import com.erubezhin.weather_sample_app.core.platfrom.BaseRepository
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.manager.network.NetworkManager
import com.erubezhin.weather_sample_app.data.manager.network.NetworkManagerImpl
import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import retrofit2.Call

/**
 * Implementation of the [WeatherRepository].
 *
 * @param context resources of the application.
 */
class WeatherRepositoryImpl(
    context: Context,
    private val networkManager: NetworkManager = NetworkManagerImpl(context),
    private val service: WeatherApi = WeatherApiService(),
) : BaseRepository(), WeatherRepository {
    override fun getTodayWeather(
        lat: Double,
        lon: Double,
    ): Response<TodayResponse> = invokeRequest { service.getTodayWeather(lat, lon) }

    override fun getHourlyWeather(
        lat: Double,
        lon: Double,
    ): Response<HourlyResponse> = invokeRequest { service.getHourlyWeather(lat, lon) }

    private inline fun <T> invokeRequest(func: () -> Call<T>): Response<T> {
        return if (networkManager.isNetworkAvailable()) {
            request(func())
        } else {
            Response.Error(NotFoundException("Internet not found"))
        }
    }
}
