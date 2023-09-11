package com.erubezhin.weather_sample_app.feature.main.cityweather

import android.Manifest
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.erubezhin.weather_sample_app.core.extension.Screens
import com.erubezhin.weather_sample_app.core.extension.logScreenOpen
import com.erubezhin.weather_sample_app.feature.common.WavesBackground
import com.erubezhin.weather_sample_app.feature.main.cityweather.widgets.*
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.textPrimary
import java.util.*

@ExperimentalPagerApi
@Composable
fun CurrentCityWeather(
    viewModel: CityWeatherViewModel = viewModel(
        factory = CityWeatherViewModel.factory(LocalContext.current.applicationContext),
    ),
) {
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

    CurrentCityScreen(viewModel)
}

@Composable
fun CurrentCityScreen(viewModel: CityWeatherViewModel) {
    AppsFlyerLib.getInstance().logScreenOpen(LocalContext.current, Screens.Main)
    val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        WavesBackground(seasonColors.wavePrimary)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = 62.dp))
            DayWidget(viewModel.currentDay.observeAsState(initial = ""))
            Spacer(modifier = Modifier.padding(top = 32.dp))
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                CheckView()
//            }
            TemperatureWidget(
                viewModel.currentTemperature.observeAsState(initial = "0"),
                seasonColors.textColor
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            ShowCity(viewModel.currentCity.observeAsState(initial = ""))
            Spacer(modifier = Modifier.padding(top = 16.dp))
            WeatherIconTypeWidget(viewModel.currentWeatherIcon.observeAsState())
            Spacer(modifier = Modifier.padding(top = 24.dp))
            LastUpdatedTimeWidget(viewModel.lastUpdatedTime.observeAsState(initial = ""))
        }
        ErrorToast(viewModel.error.observeAsState().value)
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
fun CheckView() {
    Row(
        modifier = Modifier
            .height(80.dp)
            .padding(start = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFF3F3F3), CircleShape)
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_nighttime),
                modifier = Modifier.size(72.dp),
                contentDescription = "Weather icon",
                tint = MaterialTheme.colors.textPrimary
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
        ) {
            Text(
                text = "20°",
                color = SeasonColors.AutumnColors.textColor,
                fontSize = 32.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.sp,
            )
            Text(
                text = "Kharkiv",
                color = SeasonColors.AutumnColors.textColor,
                fontSize = 24.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.sp,
            )
        }
    }
}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@ExperimentalPagerApi
fun WeathersListPreview() {
    CurrentCityWeather()
}