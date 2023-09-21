package com.erubezhin.weather_sample_app.feature.main.todayweather

import android.Manifest
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.Screens
import com.erubezhin.weather_sample_app.core.extension.logScreenOpen
import com.erubezhin.weather_sample_app.core.interactor.DataState
import com.erubezhin.weather_sample_app.feature.common.WavesBackground
import com.erubezhin.weather_sample_app.feature.main.todayweather.widgets.*
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.google.accompanist.pager.ExperimentalPagerApi
import java.util.*

@ExperimentalPagerApi
@Composable
fun TodayWeatherScreen(
    viewModel: TodayWeatherViewModel =
        viewModel(
            factory = TodayWeatherViewModel.factory(LocalContext.current.applicationContext),
        ),
) {
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) {
            if (it) {
                viewModel.loadWeather(context)
            }
        }

    SideEffect {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    CurrentCityScreen(viewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentCityScreen(viewModel: TodayWeatherViewModel) {
    AppsFlyerLib.getInstance().logScreenOpen(LocalContext.current, Screens.Today)
    val finishPx = 400f
    val pivotPx = finishPx / 2

    val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }
    val swipeState = rememberSwipeableState(initialValue = "Start")
    val anchorsMap = mapOf(0f to "Start", finishPx to "Finish")

    val eventState by viewModel.event
    val errorError by viewModel.error

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
    ) {
        WavesBackground(seasonColors.wavePrimary)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (eventState) {
                is DataState.Success -> {
                    val result = (eventState as DataState.Success<TodayWeatherModel>).data
                    Spacer(modifier = Modifier.padding(top = 62.dp))
                    DayWidget(result.day)
                    Spacer(modifier = Modifier.padding(top = 32.dp))

                    Box(
                        modifier =
                            Modifier
                                .wrapContentSize()
                                .swipeable(
                                    state = swipeState,
                                    anchors = anchorsMap,
                                    orientation = Orientation.Vertical,
                                    reverseDirection = true,
                                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                                ),
                    ) {
                        if (swipeState.offset.value > pivotPx) {
                            DetailsTempView(swipeState, result, seasonColors, finishPx)
                        } else {
                            GeneralTempView(swipeState, result, seasonColors, finishPx)
                        }
                    }
                }

                is DataState.Loading -> {
                }

                is DataState.Error -> {
                }
            }
        }
        ErrorToast(errorError)
    }
}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@ExperimentalPagerApi
fun WeathersListPreview() {
    TodayWeatherScreen()
}
