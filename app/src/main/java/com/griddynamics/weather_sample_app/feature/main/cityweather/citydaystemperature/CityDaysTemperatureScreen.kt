package com.griddynamics.weather_sample_app.feature.main.cityweather.citydaystemperature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsflyer.AppsFlyerLib
import com.griddynamics.weather_sample_app.core.extension.Screens
import com.griddynamics.weather_sample_app.core.extension.logScreenOpen
import com.griddynamics.weather_sample_app.feature.common.WavesBackground
import com.griddynamics.weather_sample_app.feature.main.cityweather.CityWeatherViewModel
import com.griddynamics.weather_sample_app.feature.splash.util.seasonMainData
import com.griddynamics.weather_sample_app.feature.ui.theme.textPrimary
import com.griddynamics.weather_sample_app.feature.ui.theme.textSecondary

@Composable
fun CityDaysTemperatureScreen(viewModel: CityWeatherViewModel) {
    val seasonData = seasonMainData
    AppsFlyerLib.getInstance().logScreenOpen(LocalContext.current, Screens.Details)

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = viewModel.currentCity.observeAsState("").value,
                    color = MaterialTheme.colors.textPrimary,
                    fontSize = 32.sp
                )
                Text(
                    text = viewModel.currentDay.observeAsState("").value,
                    color = MaterialTheme.colors.textSecondary,
                    fontSize = 20.sp
                )
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.textPrimary.copy(alpha = 0.2f)),
                        ) {
                            val icon = viewModel.currentWeatherIcon.observeAsState()

                            if (icon.value != null) {
                                Icon(
                                    painter = painterResource(id = icon.value!!),
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(8.dp),
                                    contentDescription = "Weather icon type",
                                    tint = MaterialTheme.colors.textPrimary
                                )
                            }
                        }
                    }

                    Text(
                        text = "${viewModel.currentTemperature.observeAsState("0").value}°",
                        color = seasonData.textColor,
                        fontSize = 148.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                    )
                }
            }
        }
    }
}