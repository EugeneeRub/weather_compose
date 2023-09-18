package com.erubezhin.weather_sample_app.data.model.response

import com.google.gson.annotations.SerializedName

data class HourlyResponse(
    @SerializedName("list") val weather: List<HourModel>
)

data class HourModel(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
)