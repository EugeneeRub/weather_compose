package com.erubezhin.weather_sample_app.core.extension

import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType
import java.text.DecimalFormat

/**
 * Convert kelvin temperature to default temperature types.
 *
 * @param type that should convert the kelvin value.
 */
fun Double.kelvinToTemperatureType(type: Int): String {
    val resultFormat = DecimalFormat("##")
    val result = when (TemperatureType.getTemperatureTypeByInt(type)) {
        TemperatureType.Celsius -> (this - 273.15)
        TemperatureType.Fahrenheit -> 1.8 * (this - 273.15) + 32
    }
    return resultFormat.format(result)
}