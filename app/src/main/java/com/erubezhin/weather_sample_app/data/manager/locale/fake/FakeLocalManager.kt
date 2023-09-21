package com.erubezhin.weather_sample_app.data.manager.locale.fake

import android.content.Context
import com.erubezhin.weather_sample_app.data.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.data.model.main.settings.Language

/** Fake implementation of the [LocaleManager]. */
class FakeLocalManager : LocaleManager {
    private var contextLocale: Language = Language.English
    private var languageCode: String = Language.English.code

    override fun getLanguageCode(): String = languageCode

    override fun setLanguageCode(code: String) {
        languageCode = code
    }

    override fun updateContextLocale(context: Context, language: Language) {
        contextLocale = language
    }
}
