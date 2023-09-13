package com.erubezhin.weather_sample_app.data.manager.temperature

import android.content.Context
import android.content.SharedPreferences
import com.erubezhin.weather_sample_app.BuildConfig
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType

// TODO - In future should be used via DI.
/**
 * Implementation of the [TemperatureManager].
 *
 * @param context provides access to the resources.
 */
class TemperatureManagerImpl(context: Context) : TemperatureManager {
    private val prefs: SharedPreferences = context
        .getSharedPreferences(BuildConfig.PREFS_STORAGE_KEY, Context.MODE_PRIVATE)

    override fun getTemperatureType(): Int =
        prefs.getInt(KEY_TEMPERATURE_TYPE, TemperatureType.Celsius.type)

    override fun setTemperatureType(type: Int) {
        prefs.edit().putInt(KEY_TEMPERATURE_TYPE, type).apply()
    }

    private companion object {
        const val KEY_TEMPERATURE_TYPE = "KEY_TEMPERATURE_TYPE"
    }
}