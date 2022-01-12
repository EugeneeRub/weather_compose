package com.griddynamics.weather_sample_app.screens.main.currentCityWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.griddynamics.weather_sample_app.R
import java.text.SimpleDateFormat
import java.util.*

class CurrentCityWeatherViewModel : ViewModel() {

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

    init {
        _currentDay.value =
            SimpleDateFormat("EEEE", Locale.ENGLISH).format(Calendar.getInstance().time)
        _currentTemperature.value = "23"
        _currentCity.value = "Kharkiv"
        _lastUpdatedTime.value = "14:20"

        loadWeatherIconType()
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