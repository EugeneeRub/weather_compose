package com.griddynamics.weather_sample_app.feature.main.currentCityWeather

import android.Manifest
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.griddynamics.weather_sample_app.feature.common.WavesBackground
import com.griddynamics.weather_sample_app.feature.main.currentCityWeather.cityListOfDaysTemperature.CurrentCityListOfDayTemperatureScreen
import com.griddynamics.weather_sample_app.feature.main.currentCityWeather.widgets.*
import com.griddynamics.weather_sample_app.feature.splash.util.seasonMainData
import com.griddynamics.weather_sample_app.feature.ui.theme.WeatherComposeTheme

@ExperimentalPagerApi
@Composable
@Suppress("UNUSED_PARAMETER", "NOTHING_TO_INLINE")
fun CurrentCityWeather(viewModel: CurrentCityWeatherViewModel = viewModel()) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            viewModel.loadWeather(context)
        }
    }

    SideEffect {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    WeatherComposeTheme {
        HorizontalPager(count = 2, key = { page -> "$page" }) {
            when (currentPage) {
                0 -> CurrentCityScreen(viewModel)
                1 -> CurrentCityListOfDayTemperatureScreen(viewModel)
            }
        }
    }
}

@Composable
fun CurrentCityScreen(viewModel: CurrentCityWeatherViewModel) {
    val seasonData = seasonMainData

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        WavesBackground(seasonData.wave1Color)

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
        ShowError(viewModel.error.observeAsState().value)
    }
}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@ExperimentalPagerApi
fun WeathersListPreview() {
    CurrentCityWeather()
}