package com.erubezhin.weather_sample_app.data.repository.currentWeather

import com.erubezhin.weather_sample_app.core.platfrom.Response
import com.erubezhin.weather_sample_app.data.model.response.CurrentWeatherResponse

/** Repository that helps to load weather information from the server side. */
interface WeatherRepository {

    /**
     * Load weather data by providing latitude[lat] and longitude[lon].
     *
     * @param lat latitude of the user location.
     * @param lon longitude of the user location.
     */
    fun getCurrentWeather(lat: Double, lon: Double): Response<CurrentWeatherResponse>
}