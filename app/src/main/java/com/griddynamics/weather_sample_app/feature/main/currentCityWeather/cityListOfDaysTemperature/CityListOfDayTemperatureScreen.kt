package com.griddynamics.weather_sample_app.feature.main.currentCityWeather.cityListOfDaysTemperature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.griddynamics.weather_sample_app.feature.common.WavesBackground
import com.griddynamics.weather_sample_app.feature.main.currentCityWeather.CurrentCityWeatherViewModel
import com.griddynamics.weather_sample_app.feature.main.currentCityWeather.widgets.*
import com.griddynamics.weather_sample_app.feature.splash.util.seasonMainData
import com.griddynamics.weather_sample_app.feature.ui.theme.WeatherComposeTheme

@Composable
fun CurrentCityListOfDayTemperatureScreen(viewModel: CurrentCityWeatherViewModel) {
    val seasonData = seasonMainData

    WeatherComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            WavesBackground(seasonData.wave1Color, true)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 64.dp))
                Text(text = "text")
            }
        }
    }
}