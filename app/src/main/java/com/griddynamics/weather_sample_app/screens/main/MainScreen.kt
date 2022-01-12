package com.griddynamics.weather_sample_app.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import com.griddynamics.weather_sample_app.widgets.NavDrawerItem
import com.griddynamics.weather_sample_app.widgets.WeatherDrawer
import kotlinx.coroutines.launch

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
                        modifier = Modifier.weight(5f, true), scope, navController
                    )
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

@Composable
private fun Navigation(navController: NavHostController, drawerState: DrawerState) {
    NavHost(navController, startDestination = NavDrawerItem.Home.route) {
        composable(NavDrawerItem.Home.route) {
            CurrentCityWeatherScreen(drawerState)
        }
        composable(NavDrawerItem.Settings.route) {
            SettingsScreen()
        }
    }
}
