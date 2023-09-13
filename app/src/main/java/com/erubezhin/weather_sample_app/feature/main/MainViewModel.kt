package com.erubezhin.weather_sample_app.feature.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erubezhin.weather_sample_app.data.manager.locale.LocaleManager
import com.erubezhin.weather_sample_app.data.manager.locale.LocaleManagerImpl
import com.erubezhin.weather_sample_app.data.model.main.settings.Language

/**
 * Main ViewModel that helps to work [MainScreen].
 *
 * @property localeManager helps to setup the locale of the application.
 */
class MainViewModel(
    private val localeManager: LocaleManager,
) : ViewModel() {

    /**
     * Setup [context] resources locale to use the saved language.
     *
     * @param context context of the application.
     */
    fun prepareLocale(context: Context) {
        localeManager.updateContextLocale(
            context,
            Language.getLanguageByCode(localeManager.getLanguageCode()),
        )
    }

    companion object {
        /** Provides factory of the [MainViewModel]. */
        fun factory(applicationContext: Context) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(LocaleManagerImpl(applicationContext)) as T
            }
        }
    }
}