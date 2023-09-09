package com.erubezhin.weather_sample_app.data.model.main.settings

import com.erubezhin.weather_sample_app.R

sealed class TemperatureType(val type: Int, val icon: Int) {
    object Celsius : TemperatureType(1, R.drawable.ic_celsius)
    object Fahrenheit : TemperatureType(2, R.drawable.ic_fahrenheit)

    companion object {
        fun getTemperatureTypeByInt(type: Int): TemperatureType = when (type) {
            Celsius.type -> Celsius
            Fahrenheit.type -> Fahrenheit
            else -> Celsius
        }
    }
}