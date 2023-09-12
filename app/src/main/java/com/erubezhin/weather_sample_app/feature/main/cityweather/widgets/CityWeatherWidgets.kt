package com.erubezhin.weather_sample_app.feature.main.cityweather.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.feature.ui.theme.textPrimary
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary

@Composable
fun LastUpdatedTimeWidget(timeState: State<String>) {
    Text(
        text = stringResource(id = R.string.last_updated),
        style = TextStyle(
            color = MaterialTheme.colors.textSecondary,
            fontSize = 16.sp,
        ),
    )
    Text(
        text = timeState.value,
        modifier = Modifier.padding(top = 4.dp),
        style = TextStyle(
            color = MaterialTheme.colors.textSecondary,
            fontSize = 32.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.sp,
        ),
    )
}

@Composable
fun DayWidget(currentDay: State<String>) {
    Text(
        text = currentDay.value,
        color = MaterialTheme.colors.textSecondary,
        fontSize = 22.sp,
    )
}

@Composable
fun TemperatureWidget(currentTemperature: State<String>, textColor: Color) {
    val temperature = currentTemperature.value

    TextWidget(
        text = "${temperature}°",
        textStyle = TextStyle(
            color = textColor,
            fontSize = if (temperature.length > 2) 128.sp else 156.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.sp,
        ),
    )
}

@Composable
fun TextStateWidget(
    text: State<String>,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.value,
        style = textStyle,
        modifier = modifier,
    )
}

@Composable
fun TextWidget(
    text: String,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = textStyle,
        modifier = modifier,
    )
}

@Composable
fun WeatherIconTypeWidget(
    iconState: State<Int?>,
    modifier: Modifier = Modifier
) {
    if (iconState.value == null) return
    Icon(
        painter = painterResource(id = iconState.value!!),
        modifier = modifier,
        contentDescription = "Weather icon type",
        tint = MaterialTheme.colors.textPrimary
    )
}

@Composable
fun ErrorToast(error: Throwable?) {
    if (error != null) {
        Toast.makeText(LocalContext.current, error.message, Toast.LENGTH_SHORT).show()
    }
}