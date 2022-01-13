package com.griddynamics.weather_sample_app.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griddynamics.weather_sample_app.screens.main.currentCityWeather.CurrentCityWeatherScreen
import com.griddynamics.weather_sample_app.screens.main.settings.SettingsScreen
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme
import com.griddynamics.weather_sample_app.utils.seasonMainData
import com.griddynamics.weather_sample_app.widgets.NavDrawerItem
import com.griddynamics.weather_sample_app.widgets.WeatherDrawer
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    WeatherComposeTheme {
        SetupDrawerMenu(navController) { drawerState ->
            Navigation(navController, drawerState)
        }
    }
}

@Composable
private fun SetupDrawerMenu(
    navController: NavHostController,
    content: @Composable (DrawerState) -> Unit
) {
    val seasonData = seasonMainData

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    WeatherDrawer(
                        modifier = Modifier, navController
                    )
                }
            }
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                content(drawerState)
                Box {
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
                }
            }
        }
    }

    BackHandler(enabled = drawerState.isOpen) {
        closeDrawer()
    }
}

@ExperimentalMaterialApi
@Composable
private fun Navigation(navController: NavHostController, drawerState: DrawerState) {
    NavHost(navController, startDestination = NavDrawerItem.Home.route) {
        composable(NavDrawerItem.Home.route) {
            SettingsScreen()
        }
        composable(NavDrawerItem.Settings.route) {
            CurrentCityWeatherScreen()
        }
    }
}
