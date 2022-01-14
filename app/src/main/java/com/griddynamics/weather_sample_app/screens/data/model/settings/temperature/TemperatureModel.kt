package com.griddynamics.weather_sample_app.screens.data.model.settings.temperature

import com.griddynamics.weather_sample_app.screens.data.model.settings.TemperatureType

data class TemperatureModel(
    var isSelected: Boolean = false,
    val temperature: TemperatureType
)