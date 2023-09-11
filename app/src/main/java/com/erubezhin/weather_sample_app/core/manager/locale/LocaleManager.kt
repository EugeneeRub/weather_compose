package com.erubezhin.weather_sample_app.core.manager.locale

import android.content.Context
import com.erubezhin.weather_sample_app.data.model.main.settings.Language

/**
 * Locale manager that helps to provide correct locale from the SharedPreferences.
 */
interface LocaleManager {

    /**
     * Provides language key code from the SharedPreferences storage.
     */
    fun getLanguageCode(): String

    /**
     * Updates language key via the [code].
     */
    fun setLanguageCode(code: String)

    /**
     * Updates [context] resources locale to use the required [language].
     */
    fun updateContextLocale(context: Context, language: Language)
}