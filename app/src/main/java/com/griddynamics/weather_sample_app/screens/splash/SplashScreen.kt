package com.griddynamics.weather_sample_app.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.griddynamics.weather_sample_app.screens.Screen
import com.griddynamics.weather_sample_app.utils.getSeasonalSplashData
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navigation: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1000)
        navigation.popBackStack()
        navigation.navigate(Screen.WeathersList.value)
    }
    SplashScreen(alphaAnim.value)
}

@Composable
fun SplashScreen(alpha: Float = 1f) {
    val seasonData = getSeasonalSplashData()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha),
    ) {
        val (text) = createRefs()

        Image(
            painter = painterResource(id = seasonData.image),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Splash image",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .padding(bottom = 128.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            text = "Compose Weather",
            color = seasonData.textColor,
            fontSize = 32.sp
        )
    }
}

@Preview()
@Composable
fun SplashPreview() {
    SplashScreen()
}