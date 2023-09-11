package com.erubezhin.weather_sample_app.feature.main.settings.temperature

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManager
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManagerImpl
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType
import com.erubezhin.weather_sample_app.data.model.main.settings.temperature.TemperatureModel

/**
 * Temperature ViewModel that helps to work [TemperatureDialog].
 *
 * @param temperatureManager helps to work with the temperature of the application.
 */
class TemperatureViewModel(
    private val temperatureManager: TemperatureManager,
) : ViewModel() {
    private lateinit var listOfTemperatures: List<TemperatureModel>

    private val _temperatures = MutableLiveData<List<TemperatureModel>>()
    val temperatures: LiveData<List<TemperatureModel>>
        get() = _temperatures

    init {
        prepareTemperaturesList()
    }

    private fun prepareTemperaturesList() {
        val selectedTemperature = temperatureManager.getTemperatureType()
        listOfTemperatures = TemperatureType::class.nestedClasses
            .mapNotNull { it.objectInstance as? TemperatureType }
            .map { TemperatureModel(it.type == selectedTemperature, it) }

        _temperatures.value = listOfTemperatures
    }

    /** Updates application temperature type via [tempType]. */
    fun updateTemperatureType(tempType: TemperatureType) {
        temperatureManager.setTemperatureType(tempType.type)
        listOfTemperatures.forEach {
            it.isSelected = tempType.type == it.temperature.type
        }
        _temperatures.value = listOfTemperatures
    }

    companion object {
        /** Provides factory of the [TemperatureViewModel]. */
        fun factory(applicationContext: Context) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TemperatureViewModel(TemperatureManagerImpl(applicationContext)) as T
            }
        }
    }
}