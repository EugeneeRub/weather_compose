package com.erubezhin.weather_sample_app.core.platfrom

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType
import java.util.*

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs: SharedPreferences = application
        .getSharedPreferences("key_12352312", Context.MODE_PRIVATE)

    protected fun getLanguageCode(): String =
        prefs.getString("KEY_LANGUAGE", Language.English.code)!!

    protected fun setLanguageCode(code: String) {
        prefs.edit().putString("KEY_LANGUAGE", code).apply()
    }

    protected fun getTemperatureType(): Int =
        prefs.getInt("KEY_TEMPERATURE_TYPE", TemperatureType.Celsius.type)

    protected fun setTemperatureType(type: Int) {
        prefs.edit().putInt("KEY_TEMPERATURE_TYPE", type).apply()
    }

    protected fun setLocale(context: Context, language: Language) {
        val locale = Locale(language.code)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}