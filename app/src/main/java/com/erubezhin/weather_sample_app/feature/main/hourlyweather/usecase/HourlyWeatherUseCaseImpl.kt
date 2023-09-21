package com.erubezhin.weather_sample_app.feature.main.hourlyweather.usecase

import com.erubezhin.weather_sample_app.core.interactor.UseCase
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.HourlyResponse
import com.erubezhin.weather_sample_app.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

/** Implementation of the [HourlyWeatherUseCase]. */
class HourlyWeatherUseCaseImpl(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher,
) : HourlyWeatherUseCase, UseCase<HourlyResponse, HourlyWeatherUseCase.Params>() {
    override suspend fun loadHourlyWeather(params: HourlyWeatherUseCase.Params): Response<HourlyResponse> {
        return invoke(dispatcher) { repository.getHourlyWeather(params.lat, params.lon) }
    }
}
