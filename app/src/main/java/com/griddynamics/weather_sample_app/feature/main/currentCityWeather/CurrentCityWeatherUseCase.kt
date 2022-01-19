package com.griddynamics.weather_sample_app.feature.main.currentCityWeather

import android.content.Context
import com.griddynamics.weather_sample_app.core.interactor.UseCase
import com.griddynamics.weather_sample_app.core.platfrom.RepositoryResponse
import com.griddynamics.weather_sample_app.data.model.response.CurrentWeatherResponse
import com.griddynamics.weather_sample_app.data.repository.currentWeather.WeatherRepository.WeatherRepositoryImpl

class CurrentCityWeatherUseCase(context: Context) :
    UseCase<CurrentWeatherResponse, CurrentCityWeatherUseCase.Params>() {

    private val repository by lazy { WeatherRepositoryImpl(context) }

    override suspend fun run(params: Params): RepositoryResponse<CurrentWeatherResponse> =
        repository.getCurrentWeather(params.lat, params.lon)

    data class Params(val lat: Double, val lon: Double)
}