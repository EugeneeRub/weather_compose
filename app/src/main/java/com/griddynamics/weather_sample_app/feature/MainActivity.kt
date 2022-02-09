package com.griddynamics.weather_sample_app.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.griddynamics.weather_sample_app.feature.Screen.*
import com.griddynamics.weather_sample_app.feature.main.MainScreen
import com.griddynamics.weather_sample_app.feature.splash.AnimatedSplashScreen

@ExperimentalPagerApi
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

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Splash.value
    ) {
        composable(Splash.value) { AnimatedSplashScreen(navController) }
        composable(Main.value) { MainScreen() }
    }
}

sealed class Screen(val value: String) {
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
}