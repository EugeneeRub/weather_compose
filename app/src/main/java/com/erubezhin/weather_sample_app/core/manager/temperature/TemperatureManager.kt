package com.erubezhin.weather_sample_app.core.manager.temperature

/**
 * Temperature manager that helps to provide temperature from the SharedPreferences.
 */
interface TemperatureManager {

    /**
     * Provides temperature from the SharedPreferences storage.
     */
    fun getTemperatureType(): Int

    /**
     * Updates temperature via the [type].
     */
    fun setTemperatureType(type: Int)
}