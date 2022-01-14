package com.griddynamics.weather_sample_app.screens.main.settings

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.griddynamics.weather_sample_app.screens.base.BaseViewModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.TemperatureType
import java.util.*

class SettingsViewModel(application: Application) : BaseViewModel(application) {

    private val _selectedLanguage = MutableLiveData<Language>()
    val selectedLanguage: LiveData<Language>
        get() = _selectedLanguage

    private val _selectedTemperatureType = MutableLiveData<TemperatureType>()
    val selectedTemperatureType: LiveData<TemperatureType>
        get() = _selectedTemperatureType

    init {
        _selectedLanguage.value = Language.getLanguageByCode(getLanguageCode())
        _selectedTemperatureType.value =
            TemperatureType.getTemperatureTypeByInt(getTemperatureType())
    }

    fun updateLocale(language: Language) {
        _selectedLanguage.value = language
    }

    fun updateTemperatureType(tempType: TemperatureType) {
        _selectedTemperatureType.value = tempType
    }
}