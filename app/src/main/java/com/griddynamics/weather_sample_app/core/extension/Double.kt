package com.griddynamics.weather_sample_app.core.extension

import com.griddynamics.weather_sample_app.data.model.main.settings.TemperatureType
import java.text.DecimalFormat

fun Double.kelvinToTemperatureType(type: Int): String {
    val resultFormat = DecimalFormat("##")
    val tempType = TemperatureType.getTemperatureTypeByInt(type)
    val result = when (tempType) {
        TemperatureType.Celsius -> (this - 273.15)
        TemperatureType.Fahrenheit -> 1.8 * (this - 273.15) + 32
    }
    return resultFormat.format(result)
}