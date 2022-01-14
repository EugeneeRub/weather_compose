package com.griddynamics.weather_sample_app.screens.main

import android.app.Application
import android.content.Context
import com.griddynamics.weather_sample_app.screens.base.BaseViewModel
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language

class MainViewModel(application: Application) : BaseViewModel(application) {

    fun updateLocale(context: Context) {
        setLocale(context, Language.getLanguageByCode(getLanguageCode()))
    }
}