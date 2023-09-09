package com.erubezhin.weather_sample_app.feature.main.settings.language

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erubezhin.weather_sample_app.core.platfrom.BaseViewModel
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.language.LanguageModel

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