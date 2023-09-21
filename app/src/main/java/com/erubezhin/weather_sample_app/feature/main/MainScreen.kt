package com.erubezhin.weather_sample_app.feature.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erubezhin.weather_sample_app.feature.main.hourlyweather.HourlyWeatherScreen
import com.erubezhin.weather_sample_app.feature.main.settings.SettingsScreen
import com.erubezhin.weather_sample_app.feature.main.todayweather.TodayWeatherScreen
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.WeatherComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    viewModel: MainViewModel =
        viewModel(
            factory = MainViewModel.factory(LocalContext.current.applicationContext),
        ),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) { viewModel.prepareLocale(context) }

    val navController = rememberNavController()

    WeatherComposeTheme {
        SetupDrawerMenu(navController) { drawerState ->
            Navigation(navController, drawerState)
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun SetupDrawerMenu(
    navController: NavHostController,
    content: @Composable (DrawerState) -> Unit,
) {
    val seasonColorsData = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val closeDrawer = {
        scope.launch {
            drawerState.close()
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerBackgroundColor = MaterialTheme.colors.background,
            drawerElevation = 0.dp,
            drawerContent = { WeatherDrawer(navController = navController) },
        ) {
            content(drawerState)
            Card(
                backgroundColor = seasonColorsData.wavePrimary,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp),
                modifier = Modifier.padding(top = 48.dp),
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            ) {
                Icon(
                    Icons.Rounded.Menu,
                    tint = Color.White,
                    contentDescription = "Burger icon",
                    modifier =
                        Modifier
                            .padding(12.dp)
                            .size(28.dp),
                )
            }
        }
    }

    BackHandler(enabled = drawerState.isOpen) {
        closeDrawer()
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Navigation(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()

    val closeDrawer = {
        scope.launch {
            drawerState.close()
        }
    }

    NavHost(navController, startDestination = NavDrawerItem.TodayWeather.route) {
        composable(NavDrawerItem.TodayWeather.route) {
            closeDrawer()
            TodayWeatherScreen()
        }
        composable(NavDrawerItem.HourlyWeather.route) {
            closeDrawer()
            HourlyWeatherScreen()
        }
        composable(NavDrawerItem.Settings.route) {
            closeDrawer()
            SettingsScreen()
        }
    }
}
