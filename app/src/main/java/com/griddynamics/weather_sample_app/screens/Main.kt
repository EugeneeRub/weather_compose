package com.griddynamics.weather_sample_app.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griddynamics.weather_sample_app.screens.Screen.*
import com.griddynamics.weather_sample_app.screens.splash.AnimatedSplashScreen
import com.griddynamics.weather_sample_app.screens.currentCityWeather.CurrentCityWeatherScreen

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Navigation()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Splash.value
    ) {
        composable(Splash.value) { AnimatedSplashScreen(navController) }
        composable(CurrentCityWeather.value) { CurrentCityWeatherScreen() }
    }
}

sealed class Screen(val value: String) {
    object Splash : Screen("splash_screen")
    object CurrentCityWeather : Screen("current_city_weather_screen")
}