package com.erubezhin.weather_sample_app

import android.app.Application

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        AppsFlyerLib.getInstance().init(BuildConfig.APPSFLYER_KEY, null, this)
//        AppsFlyerLib.getInstance().setDebugLog(true)
//        AppsFlyerLib.getInstance().start(this)
    }
}
