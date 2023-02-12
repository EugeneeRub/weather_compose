package com.griddynamics.weather_sample_app.data.model.main.settings

sealed class Language(val title: String, val code: String) {
    object English : Language("English", "EN")
    object Ukrainian : Language("Українська", "UK")

    companion object {
        fun getLanguageByCode(code: String): Language = when (code) {
            English.code -> English
            Ukrainian.code -> Ukrainian
            else -> English
        }
    }
}