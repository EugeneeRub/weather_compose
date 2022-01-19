package com.griddynamics.weather_sample_app.data.model.main.settings.temperature

import com.griddynamics.weather_sample_app.data.model.main.settings.TemperatureType

data class TemperatureModel(
    var isSelected: Boolean = false,
    val temperature: TemperatureType
)