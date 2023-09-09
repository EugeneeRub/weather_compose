package com.erubezhin.weather_sample_app.feature.main

import android.app.Application
import android.content.Context
import com.erubezhin.weather_sample_app.core.platfrom.BaseViewModel
import com.erubezhin.weather_sample_app.data.model.main.settings.Language

class MainViewModel(application: Application) : BaseViewModel(application) {

    fun updateLocale(context: Context) {
        setLocale(context, Language.getLanguageByCode(getLanguageCode()))
    }
}