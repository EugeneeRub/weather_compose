package com.erubezhin.weather_sample_app.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erubezhin.weather_sample_app.feature.Screen.*
import com.erubezhin.weather_sample_app.feature.main.MainScreen
import com.erubezhin.weather_sample_app.feature.splash.AnimatedSplashScreen
import com.erubezhin.weather_sample_app.feature.ui.theme.NavigationBarColorApplier
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.WeatherComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import java.util.*

@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    val seasonColorsData = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

    WeatherComposeTheme {
        NavigationBarColorApplier(seasonColorsData.wavePrimary)
        NavHost(
            navController = navController,
            startDestination = Splash.path,
        ) {
            composable(Splash.path) { AnimatedSplashScreen(navController) }
            composable(Main.path) { MainScreen() }
        }
    }
}

/** Enum that holds navigation paths. */
enum class Screen(val path: String) {
    Splash("splash_screen"),
    Main("main_screen"),
}
