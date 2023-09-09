package com.erubezhin.weather_sample_app.data.model.main.settings.temperature

import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType

data class TemperatureModel(
    var isSelected: Boolean = false,
    val temperature: TemperatureType
)