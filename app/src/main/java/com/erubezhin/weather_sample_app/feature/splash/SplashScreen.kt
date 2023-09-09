package com.erubezhin.weather_sample_app.feature.splash

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.erubezhin.weather_sample_app.feature.Screen
import com.erubezhin.weather_sample_app.feature.ui.theme.WeatherComposeTheme
import com.erubezhin.weather_sample_app.feature.ui.theme.lombokFontFamily
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import com.erubezhin.weather_sample_app.feature.common.WavesBackground
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun AnimatedSplashScreen(navigation: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(500)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)
        navigation.popBackStack()
        navigation.navigate(Screen.Main.value)
    }
    SplashScreen(alphaAnim.value)
}

@Composable
fun SplashScreen(alpha: Float = 1f) {
    val seasonColorsData = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

    WeatherComposeTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .alpha(alpha),
        ) {
            val (textMain) = createRefs()

            Text(
                modifier = Modifier
                    .padding(bottom = 128.dp)
                    .constrainAs(textMain) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                text = "Compose Weather",
                color = MaterialTheme.colors.textSecondary,
                fontSize = 32.sp,
                fontFamily = lombokFontFamily
            )

            WavesBackground(seasonColorsData.wavePrimary)
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SplashPreview() {
    SplashScreen()
}