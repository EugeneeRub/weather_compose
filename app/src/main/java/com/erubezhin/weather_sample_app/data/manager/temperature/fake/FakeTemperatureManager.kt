package com.erubezhin.weather_sample_app.data.manager.temperature.fake

import com.erubezhin.weather_sample_app.data.manager.temperature.TemperatureManager

/** Fake implementation of the [TemperatureManager]. */
class FakeTemperatureManager : TemperatureManager {
    private var tempType: Int = -1

    override fun getTemperatureType(): Int = tempType

    override fun setTemperatureType(type: Int) {
        tempType = type
    }
}
