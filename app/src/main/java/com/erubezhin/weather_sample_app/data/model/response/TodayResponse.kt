package com.erubezhin.weather_sample_app.data.model.response

import com.google.gson.annotations.SerializedName

data class TodayResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("sys") val sysModel: SysModel,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("name") val cityName: String
)