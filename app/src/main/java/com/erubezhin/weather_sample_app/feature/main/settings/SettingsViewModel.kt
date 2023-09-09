package com.erubezhin.weather_sample_app.feature.main.settings

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.logTemperatureChange
import com.erubezhin.weather_sample_app.core.platfrom.BaseViewModel
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType

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
        AppsFlyerLib.getInstance().logTemperatureChange(getApplication(), language.javaClass.simpleName)
        _selectedLanguage.value = language
    }

    fun updateTemperatureType(tempType: TemperatureType) {
        AppsFlyerLib.getInstance().logTemperatureChange(getApplication(), tempType.javaClass.simpleName)

        _selectedTemperatureType.value = tempType
    }
}