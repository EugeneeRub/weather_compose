package com.erubezhin.weather_sample_app.feature.main.hourlyweather

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erubezhin.weather_sample_app.data.manager.temperature.TemperatureManager
import com.erubezhin.weather_sample_app.data.manager.temperature.TemperatureManagerImpl
import com.erubezhin.weather_sample_app.data.repository.WeatherRepositoryImpl
import com.erubezhin.weather_sample_app.feature.main.todayweather.usecase.TodayWeatherUseCase
import com.erubezhin.weather_sample_app.feature.main.todayweather.usecase.TodayWeatherUseCaseImpl
import kotlinx.coroutines.Dispatchers

/**
 * Hourly ViewModel that helps to work [HourlyWeatherScreen].
 *
 * @property weatherUseCase helps to load the weather data from the server.
 * @property temperatureManager helps to work with the temperature of the application.
 */
class HourlyWeatherViewModel(
    private val weatherUseCase: TodayWeatherUseCase,
    private val temperatureManager: TemperatureManager,
) : ViewModel() {
    companion object {
        /** Provides factory of the [HourlyWeatherViewModel]. */
        fun factory(applicationContext: Context) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HourlyWeatherViewModel(
                        weatherUseCase =
                            TodayWeatherUseCaseImpl(
                                repository = WeatherRepositoryImpl(applicationContext),
                                dispatcher = Dispatchers.IO,
                            ),
                        temperatureManager = TemperatureManagerImpl(applicationContext),
                    ) as T
                }
            }
    }
}
