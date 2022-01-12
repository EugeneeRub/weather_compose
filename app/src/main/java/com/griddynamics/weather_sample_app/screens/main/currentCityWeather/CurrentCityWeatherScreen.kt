package com.griddynamics.weather_sample_app.screens.main.currentCityWeather

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.Card
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme
import com.griddynamics.weather_sample_app.ui.theme.textPrimary
import com.griddynamics.weather_sample_app.ui.theme.textSecondary
import com.griddynamics.weather_sample_app.utils.seasonalMainData
import com.griddynamics.weather_sample_app.widgets.WavesBackground
import kotlinx.coroutines.launch

@Composable
fun CurrentCityWeatherScreen(
    drawerState: DrawerState, viewModel: CurrentCityWeatherViewModel = viewModel()
) {
    val seasonData = seasonalMainData
    val scope = rememberCoroutineScope()

    WeatherComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            WavesBackground(seasonData.wave1Color, seasonData.wave2Color)
            Card(
                backgroundColor = seasonData.wave1Color,
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                modifier = Modifier
                    .padding(top = 56.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        scope.launch {
                            drawerState.open()
                        }
                    },
            ) {
                Icon(
                    Icons.Rounded.Menu,
                    tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(28.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 64.dp))
                ShowDay(viewModel.currentDay.observeAsState(initial = ""))
                ShowTemperature(
                    viewModel.currentTemperature.observeAsState(initial = "0"),
                    seasonData.textColor
                )
                Spacer(modifier = Modifier.padding(top = 16.dp))
                ShowCity(viewModel.currentCity.observeAsState(initial = ""))
                Spacer(modifier = Modifier.padding(top = 16.dp))
                ShowWeatherIconType(viewModel.currentWeatherIcon.observeAsState())
                Spacer(modifier = Modifier.padding(top = 24.dp))
                ShowLastUpdatedTime(viewModel.lastUpdatedTime.observeAsState(initial = ""))
            }
        }
    }
}

@Composable
fun ShowLastUpdatedTime(lastUpdatedTime: State<String>) {
    Text(
        text = "Last updated",
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

@Preview(
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun WeathersListPreview() {
    CurrentCityWeatherScreen(DrawerState(DrawerValue.Closed))
}