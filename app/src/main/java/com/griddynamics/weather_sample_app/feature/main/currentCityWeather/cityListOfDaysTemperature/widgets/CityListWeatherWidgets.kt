package com.griddynamics.weather_sample_app.feature.main.currentCityWeather.cityListOfDaysTemperature.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.griddynamics.weather_sample_app.feature.ui.theme.textSecondary

//@Composable
//fun ShowDay(currentDay: State<String>) {
//    Text(
//        text = currentDay.value,
//        color = MaterialTheme.colors.textSecondary,
//        fontSize = 22.sp
//    )
//}
//
//@Composable
//fun ShowTemperature(currentTemperature: State<String>, textColor: Color) {
//    val temperature = currentTemperature.value
//    Text(
//        text = "${temperature}°",
//        color = textColor,
//        fontSize = if (temperature.length > 2) 128.sp else 156.sp,
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Light,
//        letterSpacing = 0.sp,
//    )
//}
//
//@Composable
//fun ShowCity(currentCity: State<String>) {
//    Text(
//        text = currentCity.value,
//        color = MaterialTheme.colors.textSecondary,
//        fontSize = 32.sp
//    )
//}