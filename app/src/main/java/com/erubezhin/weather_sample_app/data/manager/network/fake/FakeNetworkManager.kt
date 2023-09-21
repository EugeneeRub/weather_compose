package com.erubezhin.weather_sample_app.data.manager.network.fake

import com.erubezhin.weather_sample_app.data.manager.network.NetworkManager

/** Fake implementation of the [NetworkManager]. */
class FakeNetworkManager : NetworkManager {
    private var isNetworkAvailable: Boolean = false

    override fun isNetworkAvailable(): Boolean = isNetworkAvailable

    private fun setNetworkAvailable(isAvailable: Boolean) {
        isNetworkAvailable = isAvailable
    }
}
