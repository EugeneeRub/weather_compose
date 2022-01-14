package com.griddynamics.weather_sample_app.screens.main.settings.temperature

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.griddynamics.weather_sample_app.screens.base.BaseViewModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.TemperatureType
import com.griddynamics.weather_sample_app.screens.data.model.settings.language.LanguageModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.temperature.TemperatureModel

class TemperatureViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var listOfTemperatures: List<TemperatureModel>

    private val _temperatures = MutableLiveData<List<TemperatureModel>>()
    val temperatures: LiveData<List<TemperatureModel>>
        get() = _temperatures

    init {
        prepareTemperaturesList()
    }

    private fun prepareTemperaturesList() {
        val selectedTemperature = getTemperatureType()
        listOfTemperatures = TemperatureType::class.nestedClasses
            .mapNotNull { it.objectInstance as? TemperatureType }
            .map { TemperatureModel(it.type == selectedTemperature, it) }

        _temperatures.value = listOfTemperatures
    }

    fun updateTemperatureType(tempType: TemperatureType) {
        setTemperatureType(tempType.type)
        listOfTemperatures.forEach {
            it.isSelected = tempType.type == it.temperature.type
        }
        _temperatures.value = listOfTemperatures
    }
}