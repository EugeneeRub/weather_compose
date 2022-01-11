package com.griddynamics.weather_sample_app.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griddynamics.weather_sample_app.screens.Screen.*
import com.griddynamics.weather_sample_app.screens.intro.IntroScreen
import com.griddynamics.weather_sample_app.screens.splash.AnimatedSplashScreen
import com.griddynamics.weather_sample_app.screens.wearherList.WeathersListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Splash.value
    ) {
        composable(Splash.value) { AnimatedSplashScreen(navController) }
        composable(Intro.value) { IntroScreen() }
        composable(WeathersList.value) { WeathersListScreen() }
    }
}

sealed class Screen(val value: String) {
    object Splash : Screen("splash_screen")
    object Intro : Screen("intro_screen")
    object WeathersList : Screen("weathers_list_screen")
}