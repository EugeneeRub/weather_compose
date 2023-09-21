package com.erubezhin.weather_sample_app.data.manager.network

import android.net.ConnectivityManager

/**
 * Network manager that helps to check does the device has internet connection.
 */
interface NetworkManager {
    /**
     * Via the [ConnectivityManager] checks that device has internet connection.
     *
     * Returns true if at least one transport has connection, otherwise false.
     */
    fun isNetworkAvailable(): Boolean
}
