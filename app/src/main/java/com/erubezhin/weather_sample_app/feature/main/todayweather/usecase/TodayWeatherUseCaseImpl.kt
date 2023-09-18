package com.erubezhin.weather_sample_app.feature.main.todayweather.usecase

import com.erubezhin.weather_sample_app.core.interactor.UseCase
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import com.erubezhin.weather_sample_app.data.repository.currentWeather.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

class TodayWeatherUseCaseImpl(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher,
) : TodayWeatherUseCase, UseCase<TodayResponse, TodayWeatherUseCase.Params>() {

    override suspend fun run(params: TodayWeatherUseCase.Params): Response<TodayResponse> =
        repository.getTodayWeather(params.lat, params.lon)

    override suspend fun loadCurrentWeather(params: TodayWeatherUseCase.Params): Response<TodayResponse> =
        invoke(params, dispatcher)
}
