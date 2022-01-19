package com.griddynamics.weather_sample_app.data.model.main.settings.language

import com.griddynamics.weather_sample_app.data.model.main.settings.Language

data class LanguageModel(
    var isSelected: Boolean = false,
    val locale: Language
)