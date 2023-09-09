package com.erubezhin.weather_sample_app.core.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringTime(): String =
    SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)