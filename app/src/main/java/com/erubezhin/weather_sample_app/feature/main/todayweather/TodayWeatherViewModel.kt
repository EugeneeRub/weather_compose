package com.erubezhin.weather_sample_app.feature.main.todayweather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.core.extension.AppsFlyerLocation
import com.erubezhin.weather_sample_app.core.extension.kelvinToTemperatureType
import com.erubezhin.weather_sample_app.core.extension.logLocation
import com.erubezhin.weather_sample_app.core.extension.toStringTime
import com.erubezhin.weather_sample_app.core.interactor.DataState
import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.data.manager.locale.LocaleManagerImpl
import com.erubezhin.weather_sample_app.data.manager.temperature.TemperatureManager
import com.erubezhin.weather_sample_app.data.manager.temperature.TemperatureManagerImpl
import com.erubezhin.weather_sample_app.data.model.response.TodayResponse
import com.erubezhin.weather_sample_app.data.repository.WeatherRepositoryImpl
import com.erubezhin.weather_sample_app.feature.main.settings.SettingsScreen
import com.erubezhin.weather_sample_app.feature.main.todayweather.usecase.TodayWeatherUseCase
import com.erubezhin.weather_sample_app.feature.main.todayweather.usecase.TodayWeatherUseCaseImpl
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Settings ViewModel that helps to work [SettingsScreen].
 *
 * @property weatherUseCase helps to load the weather data from the server.
 * @property temperatureManager helps to work with the temperature of the application.
 * @param localeManager helps to work with the locale of the application.
 */
class TodayWeatherViewModel(
    private val weatherUseCase: TodayWeatherUseCase,
    private val temperatureManager: TemperatureManager,
    localeManager: LocaleManager,
) : ViewModel() {
    private var currentDay: String

    private val _event: MutableState<DataState<TodayWeatherModel>> =
        mutableStateOf(DataState.Loading)
    val event: State<DataState<TodayWeatherModel>>
        get() = _event

    private val _error: MutableState<Throwable?> = mutableStateOf(null)
    val error: State<Throwable?>
        get() = _error

    init {
        currentDay =
            SimpleDateFormat(
                "EEEE", Locale(localeManager.getLanguageCode().lowercase()),
            ).format(Calendar.getInstance().time)
    }

    /**
     * Load the weather info from the server if [Manifest.permission.ACCESS_FINE_LOCATION]
     * permission was provided
     *
     * @param context resources of the application.
     */
    fun loadWeather(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationService = LocationServices.getFusedLocationProviderClient(context)

            locationService.lastLocation
                .addOnSuccessListener {
                    loadCurrentWeather(context, it.latitude, it.longitude)
                }
                .addOnFailureListener {
                    _error.value = it
                }
        }
    }

    private fun loadCurrentWeather(
        context: Context,
        lat: Double,
        lon: Double,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = weatherUseCase.loadCurrentWeather(TodayWeatherUseCase.Params(lat, lon))
            when (response) {
                is Response.Result -> {
                    _event.value = DataState.Success(response.result.toTodayWeatherModel())

                    AppsFlyerLib.getInstance().logLocation(
                        context,
                        AppsFlyerLocation(
                            latitude = lat,
                            longitude = lon,
                            response.result.cityName,
                        ),
                    )
                }

                is Response.Error -> {
                    _error.value = response.throwable
                }
            }
        }
    }

    private fun getWeatherIconById(iconId: String) =
        when (iconId) {
            "01d" -> R.drawable.ic_sunny
            "01n" -> R.drawable.ic_nighttime
            "02d" -> R.drawable.ic_partly_sunny
            "02n" -> R.drawable.ic_partly_nighttime
            "03d", "03n", "04d", "04n" -> R.drawable.ic_overcast
            "09d", "09n" -> R.drawable.ic_rain
            "10d" -> R.drawable.ic_occasional_showers
            "10n" -> R.drawable.ic_partly_showers_nighttime
            "11d" -> R.drawable.ic_partly_thunder_sunny
            "11n" -> R.drawable.ic_partly_thunder_nighttime
            "13d", "13n" -> R.drawable.ic_snow
            else -> R.drawable.ic_sunny
        }

    private fun TodayResponse.toTodayWeatherModel(): TodayWeatherModel {
        val tempType = temperatureManager.getTemperatureType()

        return TodayWeatherModel(
            day = currentDay,
            temperature = main.temp.kelvinToTemperatureType(tempType),
            tempFeelsLike = main.feelsLikeTemp.kelvinToTemperatureType(tempType),
            city = cityName,
            lastUpdatedTime = Calendar.getInstance().time.toStringTime(),
            weatherIconId =
                getWeatherIconById(
                    weather.firstOrNull()?.icon ?: "",
                ),
            details =
                DetailsModel(
                    tempMin = main.tempMin.kelvinToTemperatureType(tempType),
                    tempMax = main.tempMax.kelvinToTemperatureType(tempType),
                    wind = (wind.speed * 3.293).toInt(),
                    pressure = main.pressure,
                    humidity = main.humidity,
                    sunrise = sysModel.sunrise,
                    sunset = sysModel.sunset,
                    visibility = DetailsModel.Visibility.getVisibility(visibility),
                ),
        )
    }

    companion object {
        /** Provides factory of the [TodayWeatherViewModel]. */
        fun factory(applicationContext: Context) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodayWeatherViewModel(
                        weatherUseCase =
                            TodayWeatherUseCaseImpl(
                                repository = WeatherRepositoryImpl(applicationContext),
                                dispatcher = Dispatchers.IO,
                            ),
                        localeManager = LocaleManagerImpl(applicationContext),
                        temperatureManager = TemperatureManagerImpl(applicationContext),
                    ) as T
                }
            }
    }
}
