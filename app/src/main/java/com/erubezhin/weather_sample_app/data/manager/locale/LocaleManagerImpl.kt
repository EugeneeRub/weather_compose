package com.erubezhin.weather_sample_app.data.manager.locale

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import com.erubezhin.weather_sample_app.BuildConfig
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import java.util.*

// TODO - In future should be used via DI.

/**
 * Implementation of the [LocaleManager].
 *
 * @param context provides access to the resources.
 */
class LocaleManagerImpl(context: Context) : LocaleManager {
    private val prefs: SharedPreferences =
        context
            .getSharedPreferences(BuildConfig.PREFS_STORAGE_KEY, Context.MODE_PRIVATE)

    override fun getLanguageCode(): String = prefs.getString(KEY_LANGUAGE, Language.English.code)!!

    override fun setLanguageCode(code: String) {
        prefs.edit().putString(KEY_LANGUAGE, code).apply()
    }

    override fun updateContextLocale(
        context: Context,
        language: Language,
    ) {
        val locale = Locale(language.code)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private companion object {
        const val KEY_LANGUAGE = "KEY_LANGUAGE"
    }
}
