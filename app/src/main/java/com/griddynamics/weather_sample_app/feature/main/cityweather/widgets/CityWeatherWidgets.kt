package com.griddynamics.weather_sample_app.feature.main.cityweather.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griddynamics.weather_sample_app.R
import com.griddynamics.weather_sample_app.feature.ui.theme.textPrimary
import com.griddynamics.weather_sample_app.feature.ui.theme.textSecondary

@Composable
fun ShowLastUpdatedTime(lastUpdatedTime: State<String>) {
    Text(
        text = stringResource(id = R.string.last_updated),
        color = MaterialTheme.colors.textSecondary,
        fontSize = 16.sp,
    )
    Text(
        text = lastUpdatedTime.value,
        modifier = Modifier.padding(top = 4.dp),
        color = MaterialTheme.colors.textSecondary,
        fontSize = 32.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        letterSpacing = 0.sp,
    )
}

@Composable
fun ShowDay(currentDay: State<String>) {
    Text(
        text = currentDay.value,
        color = MaterialTheme.colors.textSecondary,
        fontSize = 22.sp
    )
}

@Composable
fun ShowTemperature(currentTemperature: State<String>, textColor: Color) {
    val temperature = currentTemperature.value
    Text(
        text = "${temperature}°",
        color = textColor,
        fontSize = if (temperature.length > 2) 128.sp else 156.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        letterSpacing = 0.sp,
    )
}

@Composable
fun ShowCity(currentCity: State<String>) {
    Text(
        text = currentCity.value,
        color = MaterialTheme.colors.textSecondary,
        fontSize = 32.sp
    )
}

@Composable
fun ShowWeatherIconType(icon: State<Int?>) {
    if (icon.value == null) return
    Icon(
        painter = painterResource(id = icon.value!!),
        modifier = Modifier.size(156.dp),
        contentDescription = "Weather icon type",
        tint = MaterialTheme.colors.textPrimary
    )
}

@Composable
fun ShowError(error: Throwable?) {
    if (error != null) {
        Toast.makeText(LocalContext.current, error.message, Toast.LENGTH_SHORT).show()
    }
}