package com.erubezhin.weather_sample_app.data.model.main.settings

/** Represents language of the application. */
sealed class Language(val title: String, val code: String) {
    object English : Language("English", "EN")
    object Ukrainian : Language("Українська", "UK")

    companion object {
        /** Via the provided [code] value returns required [Language]. */
        fun getLanguageByCode(code: String): Language =
            when (code) {
                English.code -> English
                Ukrainian.code -> Ukrainian
                else -> English
            }
    }
}
