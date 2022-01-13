package com.griddynamics.weather_sample_app.screens.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.TemperatureType

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
}