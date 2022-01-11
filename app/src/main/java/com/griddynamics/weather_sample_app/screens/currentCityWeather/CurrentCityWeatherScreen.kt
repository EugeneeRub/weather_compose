package com.griddynamics.weather_sample_app.screens.currentCityWeather

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme
import com.griddynamics.weather_sample_app.ui.theme.textPrimary
import com.griddynamics.weather_sample_app.ui.theme.textSecondary
import com.griddynamics.weather_sample_app.utils.getSeasonalSplashData
import com.griddynamics.weather_sample_app.widgets.WavesBackground
import com.griddynamics.weather_sample_app.widgets.weatherDrawerShape
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun CurrentCityWeatherScreen(viewModel: CurrentCityWeatherViewModel = viewModel()) {
    val seasonData = getSeasonalSplashData()

    val scope = rememberCoroutineScope()

    WeatherComposeTheme {
        SetupDrawerMenu { drawerState ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                WavesBackground(seasonData.wave1Color, seasonData.wave2Color)

                Card(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    backgroundColor = seasonData.wave1Color,
                    modifier = Modifier
                        .padding(top = 56.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
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

@Composable
fun SetupDrawerMenu(content: @Composable (DrawerState) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val closeDrawer = {
        scope.launch {
            drawerState.close()
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerBackgroundColor = MaterialTheme.colors.background,
            drawerElevation = 0.dp,
            drawerShape = weatherDrawerShape,
            drawerContent = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(top = 56.dp))
                    Text("Some title", color = Color.White)
                }
            }
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                content(drawerState)
            }
        }
    }

    BackHandler(enabled = drawerState.isOpen) {
        closeDrawer()
    }
}

@ExperimentalMaterialApi
//@Preview(
//    name = "Light mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
@Preview(
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun WeathersListPreview() {
    CurrentCityWeatherScreen()
}