package com.griddynamics.weather_sample_app.feature.main.currentCityWeather

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.griddynamics.weather_sample_app.R
import com.griddynamics.weather_sample_app.core.extension.toStringTemperature
import com.griddynamics.weather_sample_app.core.extension.toStringTime
import com.griddynamics.weather_sample_app.core.platfrom.BaseViewModel
import com.griddynamics.weather_sample_app.core.platfrom.RepositoryResponse
import java.text.SimpleDateFormat
import java.util.*

class CurrentCityWeatherViewModel(application: Application) : BaseViewModel(application) {

    private val weatherUseCase by lazy { CurrentCityWeatherUseCase(application) }

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
            "EEEE", Locale(getLanguageCode().lowercase())
        ).format(Calendar.getInstance().time)
        _currentDay.value = day

        loadWeatherIconType()
    }

    fun loadWeather(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationService = LocationServices.getFusedLocationProviderClient(context)

            locationService.lastLocation
                .addOnSuccessListener {
                    loadCurrentWeather(it.latitude, it.longitude)
                }
                .addOnFailureListener {
                    _error.value = it
                }
        }
    }

    private fun loadCurrentWeather(lat: Double, lon: Double) {
        weatherUseCase.invoke(CurrentCityWeatherUseCase.Params(lat, lon), viewModelScope) {
            when (it) {
                is RepositoryResponse.Result -> {
                    it.result.let { value ->
                        _currentTemperature.value = value.main.temp.toStringTemperature()
                        _currentCity.value = "City"
                        _lastUpdatedTime.value = Calendar.getInstance().time.toStringTime()
                    }
                }
                is RepositoryResponse.Error -> {
                    _error.value = it.throwable
                }
            }
        }
    }

    private fun loadWeatherIconType() {
        val loadedType = Random().nextInt(18)
        _currentWeatherIcon.value = when (loadedType) {
            1 -> R.drawable.ic_nighttime
            2 -> R.drawable.ic_nighttime_windy
            3 -> R.drawable.ic_occasional_showers
            4 -> R.drawable.ic_overcast
            5 -> R.drawable.ic_partly_nighttime
            6 -> R.drawable.ic_partly_showers_nighttime
            7 -> R.drawable.ic_partly_snow
            8 -> R.drawable.ic_partly_sunny
            9 -> R.drawable.ic_partly_thunder_nighttime
            10 -> R.drawable.ic_partly_thunder_sunny
            11 -> R.drawable.ic_rain
            12 -> R.drawable.ic_snow
            13 -> R.drawable.ic_storm
            14 -> R.drawable.ic_sunny
            15 -> R.drawable.ic_sunny_windy
            16 -> R.drawable.ic_thunder
            17 -> R.drawable.ic_wet
            18 -> R.drawable.ic_windy
            else -> R.drawable.ic_sunny
        }

    }
}