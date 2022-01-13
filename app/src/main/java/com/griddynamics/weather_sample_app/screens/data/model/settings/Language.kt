package com.griddynamics.weather_sample_app.screens.data.model.settings

sealed class Language(val title: String, val code: String) {
    object English : Language("English", "en")
    object Russian : Language("Русский", "ru")

    companion object {
        fun getLanguageByCode(code: String): Language = when (code) {
            English.code -> English
            Russian.code -> Russian
            else -> English
        }
    }
}