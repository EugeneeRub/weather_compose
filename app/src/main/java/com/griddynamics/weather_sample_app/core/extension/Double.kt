package com.griddynamics.weather_sample_app.core.extension

import java.text.DecimalFormat

fun Double.toStringTemperature(): String = DecimalFormat("##").format(this)