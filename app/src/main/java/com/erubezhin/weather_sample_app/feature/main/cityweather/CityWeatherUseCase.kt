package com.erubezhin.weather_sample_app.feature.main.cityweather

import android.content.Context
import com.erubezhin.weather_sample_app.core.interactor.UseCase
import com.erubezhin.weather_sample_app.core.platfrom.RepositoryResponse
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse
import com.erubezhin.weather_sample_app.data.repository.currentWeather.WeatherRepository.WeatherRepositoryImpl

class CityWeatherUseCase(context: Context) :
    UseCase<CurrentWeatherResponse, CityWeatherUseCase.Params>() {

    private val repository by lazy { WeatherRepositoryImpl(context) }

    override suspend fun run(params: Params): RepositoryResponse<CurrentWeatherResponse> =
        repository.getCurrentWeather(params.lat, params.lon)

    data class Params(val lat: Double, val lon: Double)
}