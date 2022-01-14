package com.griddynamics.weather_sample_app.screens.data.model.settings.language

import com.griddynamics.weather_sample_app.screens.data.model.settings.Language

data class LanguageModel(
    var isSelected: Boolean = false,
    val locale: Language
)