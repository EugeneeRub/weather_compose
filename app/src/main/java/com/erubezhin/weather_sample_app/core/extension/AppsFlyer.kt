package com.erubezhin.weather_sample_app.core.extension

import android.content.Context
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib

private const val SCREEN_OPENED = "SCREEN OPENED"
private const val DIALOG_OPENED = "DIALOG OPENED"
private const val LANGUAGE_CHANGE = "LANGUAGE CHANGE"
private const val TEMPERATURE_CHANGE = "TEMPERATURE CHANGE"

//Content
private const val LANGUAGE = "LANGUAGE"
private const val TEMPERATURE = "TEMPERATURE"

fun AppsFlyerLib.logScreenOpen(context: Context, screen: Screens) {
    logEvent(context, SCREEN_OPENED, mapOf(AFInAppEventParameterName.CONTENT_TYPE to screen))
}

fun AppsFlyerLib.logDialogOpen(context: Context, dialog: Dialogs) {
    logEvent(context, DIALOG_OPENED, mapOf(AFInAppEventParameterName.CONTENT_TYPE to dialog))
}

fun AppsFlyerLib.logLanguageChange(context: Context, lang: String) {
    logEvent(context, LANGUAGE_CHANGE, mapOf(LANGUAGE to lang))
}

fun AppsFlyerLib.logTemperatureChange(context: Context, temperature: String) {
    logEvent(context, TEMPERATURE_CHANGE, mapOf(TEMPERATURE to temperature))
}

fun AppsFlyerLib.logLocation(context: Context, location: AppsFlyerLocation) {
    logEvent(
        context,
        AFInAppEventType.LOCATION_CHANGED,
        mapOf(
            AFInAppEventParameterName.CITY to location.city,
            AFInAppEventParameterName.LATITUDE to location.latitude,
            AFInAppEventParameterName.LONGTITUDE to location.longitude
        )
    )
}

data class AppsFlyerLocation(
    val latitude: Double,
    val longitude: Double,
    val city: String,
)

enum class Screens {
    Main, Settings
}

enum class Dialogs {
    Language, Temperature
}

