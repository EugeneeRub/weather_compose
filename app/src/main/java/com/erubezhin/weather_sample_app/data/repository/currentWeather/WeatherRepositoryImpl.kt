package com.erubezhin.weather_sample_app.data.repository.currentWeather

import android.content.Context
import com.erubezhin.weather_sample_app.core.platfrom.BaseRepository
import com.erubezhin.weather_sample_app.core.platfrom.NetworkHandler
import com.erubezhin.weather_sample_app.core.platfrom.RepositoryResponse
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse
import java.lang.Exception

/**
 * Implementation of the [WeatherRepository].
 *
 * @param context resources of the application.
 */
class WeatherRepositoryImpl(context: Context) : BaseRepository(), WeatherRepository {
    private val networkHandler = NetworkHandler(context)
    private val service = WeatherApiService()

    override fun getCurrentWeather(
        lat: Double, lon: Double
    ): RepositoryResponse<CurrentWeatherResponse> = if (networkHandler.isNetworkAvailable()) {
        request(service.getGeoDataByCoordinates(lat, lon))
    } else {
        RepositoryResponse.Error(Exception("Internet not found"))
    }
}