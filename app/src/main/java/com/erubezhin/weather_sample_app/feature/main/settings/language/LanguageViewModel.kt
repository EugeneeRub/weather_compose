package com.erubezhin.weather_sample_app.feature.main.settings.language

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.core.manager.locale.LocaleManagerImpl
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.language.LanguageModel

/**
 * Language ViewModel that helps to work [LanguageDialog].
 *
 * @property localeManager helps to work with the locale of the application.
 */
class LanguageViewModel(
    private val localeManager: LocaleManager,
) : ViewModel() {
    private lateinit var listOfLanguages: List<LanguageModel>

    private val _languages = MutableLiveData<List<LanguageModel>>()
    val languages: LiveData<List<LanguageModel>>
        get() = _languages

    init {
        prepareLocaleList()
    }

    private fun prepareLocaleList() {
        val selectedLanguageCode = localeManager.getLanguageCode()
        listOfLanguages = Language::class.nestedClasses
            .mapNotNull { it.objectInstance as? Language }
            .map { LanguageModel(it.code == selectedLanguageCode, it) }

        _languages.value = listOfLanguages
    }

    /** Updates application [context] locale via [language]. */
    fun updateLanguage(context: Context, language: Language) {
        localeManager.setLanguageCode(language.code)
        localeManager.updateContextLocale(context, language)
        listOfLanguages.forEach {
            it.isSelected = language.code == it.locale.code
        }
        _languages.value = listOfLanguages
    }

    companion object {
        /** Provides factory of the [LanguageViewModel]. */
        fun factory(applicationContext: Context) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LanguageViewModel(LocaleManagerImpl(applicationContext)) as T
            }
        }
    }
}