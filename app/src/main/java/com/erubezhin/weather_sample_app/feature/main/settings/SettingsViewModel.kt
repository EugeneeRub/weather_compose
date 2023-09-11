package com.erubezhin.weather_sample_app.feature.main.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.logTemperatureChange
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManagerImpl
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManager
import com.erubezhin.weather_sample_app.core.manager.temperature.TemperatureManagerImpl
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType

/**
 * Settings ViewModel that helps to work [SettingsScreen].
 *
 * @param localeManager helps to work with the locale of the application.
 * @param temperatureManager helps to work with the temperature of the application.
 */
class SettingsViewModel(
    localeManager: LocaleManager,
    temperatureManager: TemperatureManager,
) : ViewModel() {

    private val _selectedLanguage = MutableLiveData<Language>()
    val selectedLanguage: LiveData<Language>
        get() = _selectedLanguage

    private val _selectedTemperatureType = MutableLiveData<TemperatureType>()
    val selectedTemperatureType: LiveData<TemperatureType>
        get() = _selectedTemperatureType

    init {
        _selectedLanguage.value = Language.getLanguageByCode(localeManager.getLanguageCode())
        _selectedTemperatureType.value =
            TemperatureType.getTemperatureTypeByInt(temperatureManager.getTemperatureType())
    }

    /**
     * Updates locale via [language].
     *
     * @param context for log the events.
     * @param language required language to be changed in app.
     */
    fun updateLocale(context: Context, language: Language) {
        AppsFlyerLib.getInstance().logTemperatureChange(context, language.javaClass.simpleName)
        _selectedLanguage.value = language
    }

    /**
     * Updates temperature type via [tempType].
     *
     * @param context for log the events.
     * @param tempType required temperature type to be changed in app.
     */
    fun updateTemperatureType(context: Context, tempType: TemperatureType) {
        AppsFlyerLib.getInstance().logTemperatureChange(context, tempType.javaClass.simpleName)
        _selectedTemperatureType.value = tempType
    }

    companion object {
        /** Provides factory of the [SettingsViewModel]. */
        fun factory(applicationContext: Context) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(
                    LocaleManagerImpl(applicationContext),
                    TemperatureManagerImpl(applicationContext),
                ) as T
            }
        }
    }
}