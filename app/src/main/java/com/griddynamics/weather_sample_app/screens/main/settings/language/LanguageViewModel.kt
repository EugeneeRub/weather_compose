package com.griddynamics.weather_sample_app.screens.main.settings.language

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.griddynamics.weather_sample_app.screens.base.BaseViewModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.language.LanguageModel

class LanguageViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var listOfLanguages: List<LanguageModel>

    private val _languages = MutableLiveData<List<LanguageModel>>()
    val languages: LiveData<List<LanguageModel>>
        get() = _languages

    init {
        prepareLocaleList()
    }

    private fun prepareLocaleList() {
        val selectedLanguageCode = getLanguageCode()
        listOfLanguages = Language::class.nestedClasses
            .mapNotNull { it.objectInstance as? Language }
            .map { LanguageModel(it.code == selectedLanguageCode, it) }

        _languages.value = listOfLanguages
    }

    fun updateLanguage(context: Context, language: Language) {
        setLanguageCode(language.code)
        setLocale(context, language)
        listOfLanguages.forEach {
            it.isSelected = language.code == it.locale.code
        }
        _languages.value = listOfLanguages
    }
}