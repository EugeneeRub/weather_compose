package com.erubezhin.weather_sample_app.feature.main.cityweather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.location.LocationServices
import com.erubezhin.weather_sample_app.core.extension.AppsFlyerLocation
import com.erubezhin.weather_sample_app.core.extension.kelvinToTemperatureType
import com.erubezhin.weather_sample_app.core.extension.logLocation
import com.erubezhin.weather_sample_app.core.extension.toStringTime
import com.erubezhin.weather_sample_app.core.platfrom.RepositoryResponse
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManagerImpl
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManager
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManagerImpl
import com.erubezhin.weather_sample_app.feature.main.settings.SettingsScreen
import java.text.SimpleDateFormat
import java.util.*

/**
 * Settings ViewModel that helps to work [SettingsScreen].
 *
 * @property weatherUseCase helps to load the weather data from the server.
 * @property temperatureManager helps to work with the temperature of the application.
 * @param localeManager helps to work with the locale of the application.
 */
class CityWeatherViewModel(
    private val weatherUseCase: CityWeatherUseCase,
    private val temperatureManager: TemperatureManager,
    localeManager: LocaleManager,
) : ViewModel() {

    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String>
        get() = _currentDay

    private val _currentTemperature = MutableLiveData<String>()
    val currentTemperature: LiveData<String>
        get() = _currentTemperature

    private val _currentCity = MutableLiveData<String>()
    val currentCity: LiveData<String>
        get() = _currentCity

    private val _lastUpdatedTime = MutableLiveData<String>()
    val lastUpdatedTime: LiveData<String>
        get() = _lastUpdatedTime

    private val _currentWeatherIcon = MutableLiveData<Int>()
    val currentWeatherIcon: LiveData<Int>
        get() = _currentWeatherIcon

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    init {
        val day = SimpleDateFormat(
            "EEEE", Locale(localeManager.getLanguageCode().lowercase())
        ).format(Calendar.getInstance().time)
        _currentDay.value = day
    }

    /**
     * Load the weather info from the server if [Manifest.permission.ACCESS_FINE_LOCATION]
     * permission was provided
     *
     * @param context resources of the application.
     */
    fun loadWeather(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
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

    private fun loadCurrentWeather(context: Context, lat: Double, lon: Double) {
        weatherUseCase.invoke(CityWeatherUseCase.Params(lat, lon), viewModelScope) {
            when (it) {
                is RepositoryResponse.Result -> {
                    it.result.let { value ->
                        _currentTemperature.value =
                            value.main.temp.kelvinToTemperatureType(temperatureManager.getTemperatureType())
                        _currentCity.value = value.cityName
                        _lastUpdatedTime.value = Calendar.getInstance().time.toStringTime()
                        value.weather.firstOrNull()?.let { weather ->
                            loadWeatherIconType(weather.icon)
                        }

                        AppsFlyerLib.getInstance().logLocation(
                            context,
                            AppsFlyerLocation(
                                latitude = lat,
                                longitude = lon,
                                value.cityName,
                            )
                        )
                    }
                }

                is RepositoryResponse.Error -> {
                    _error.value = it.throwable
                }
            }
        }
    }

    private fun loadWeatherIconType(iconId: String) {
        _currentWeatherIcon.value = when (iconId) {
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
//            13 -> R.drawable.ic_storm
//            15 -> R.drawable.ic_sunny_windy
//            16 -> R.drawable.ic_thunder
//            17 -> R.drawable.ic_wet
//            18 -> R.drawable.ic_windy
            else -> R.drawable.ic_sunny
        }
    }

    companion object {
        /** Provides factory of the [CityWeatherViewModel]. */
        fun factory(applicationContext: Context) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CityWeatherViewModel(
                    weatherUseCase = CityWeatherUseCase(applicationContext),
                    localeManager = LocaleManagerImpl(applicationContext),
                    temperatureManager = TemperatureManagerImpl(applicationContext),
                ) as T
            }
        }
    }
}