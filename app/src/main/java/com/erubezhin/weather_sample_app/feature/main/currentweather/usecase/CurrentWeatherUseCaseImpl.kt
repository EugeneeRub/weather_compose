package com.erubezhin.weather_sample_app.feature.main.currentweather.usecase

import com.erubezhin.weather_sample_app.core.interactor.UseCase
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse
import com.erubezhin.weather_sample_app.data.repository.currentWeather.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

class CurrentWeatherUseCaseImpl(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher,
) : CurrentWeatherUseCase, UseCase<CurrentWeatherResponse, CurrentWeatherUseCase.Params>() {

    override suspend fun run(params: CurrentWeatherUseCase.Params): Response<CurrentWeatherResponse> =
        repository.getCurrentWeather(params.lat, params.lon)

    override suspend fun loadCurrentWeather(params: CurrentWeatherUseCase.Params): Response<CurrentWeatherResponse> =
        invoke(params, dispatcher)
}
