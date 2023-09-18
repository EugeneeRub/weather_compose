package com.erubezhin.weather_sample_app.feature.main.todayweather

import android.Manifest
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.erubezhin.weather_sample_app.core.extension.Screens
import com.erubezhin.weather_sample_app.core.extension.logScreenOpen
import com.erubezhin.weather_sample_app.core.interactor.DataState
import com.erubezhin.weather_sample_app.feature.common.WavesBackground
import com.erubezhin.weather_sample_app.feature.main.todayweather.widgets.*
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.textPrimary
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import com.erubezhin.weather_sample_app.feature.ui.theme.viewSelected
import java.util.*

@ExperimentalPagerApi
@Composable
fun TodayWeatherScreen(
    viewModel: TodayWeatherViewModel = viewModel(
        factory = TodayWeatherViewModel.factory(LocalContext.current.applicationContext),
    ),
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
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

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .swipeable(state = swipeState,
                            anchors = anchorsMap,
                            orientation = Orientation.Vertical,
                            reverseDirection = true,
                            thresholds = { _, _ -> FractionalThreshold(0.5f) }
                        )
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsTempView(
    swipeState: SwipeableState<String>,
    model: TodayWeatherModel,
    seasonColors: SeasonColors,
    finishPx: Float,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .graphicsLayer {
            alpha = swipeState.offset.value / finishPx
        }) {
        Row(
            modifier = Modifier
                .height(92.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.viewSelected, CircleShape)
                    .padding(8.dp),
            ) {
                WeatherIconTypeWidget(
                    model.weatherIconId,
                    modifier = Modifier.size(72.dp),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
            ) {
                TextWidget(
                    text = stringResource(R.string.terminology_feels_like),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.textPrimary, fontSize = 22.sp
                    ),
                )
                TextWidget(
                    model.getTemperatureFeelsLikeString(),
                    textStyle = TextStyle(
                        color = seasonColors.wavePrimary, fontSize = 32.sp
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .size(92.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.viewSelected,
                elevation = 0.dp,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextWidget(
                        text =  model.details.getSunriseTime(),
                        modifier = Modifier.padding(bottom = 4.dp),
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                        ),
                    )
                    TextWidget(
                        text = stringResource(R.string.terminology_sunrise),
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                        ),
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .size(92.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.viewSelected,
                elevation = 0.dp,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextWidget(
                        text =  model.details.getSunsetTime(),
                        modifier = Modifier
                            .padding(bottom = 4.dp),
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                        ),
                    )
                    TextWidget(
                        text = stringResource(R.string.terminology_sunset),
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                        ),
                    )
                }
            }
        }

        DetailsWeatherItem(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_min_max_temp),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
            TextWidget(
                text = model.details.getMinMaxTemperatureString(),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
        }
        DetailsWeatherItem(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_wind),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
            TextWidget(
                text = stringResource(id = R.string.terminology_km_hour_format, model.details.wind),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
        }
        DetailsWeatherItem(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_humidity),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
            TextWidget(
                text = stringResource(
                    id = R.string.terminology_mbar_format,
                    model.details.humidity
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
        }
        DetailsWeatherItem(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_visibility),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
            TextWidget(
                text = stringResource(model.details.visibility.stringId),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textPrimary, fontSize = 18.sp
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GeneralTempView(
    swipeState: SwipeableState<String>,
    model: TodayWeatherModel,
    seasonColors: SeasonColors,
    finishPx: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = (finishPx - swipeState.offset.value) / finishPx
            },
    ) {
        TemperatureWidget(model.getTemperatureString(), seasonColors.textColor)
        Spacer(
            modifier = Modifier.padding(top = 16.dp),
        )
        TextWidget(
            model.city,
            textStyle = TextStyle(
                color = MaterialTheme.colors.textSecondary, fontSize = 32.sp
            ),
        )
        Spacer(
            modifier = Modifier.padding(top = 16.dp),
        )
        WeatherIconTypeWidget(
            model.weatherIconId,
            modifier = Modifier.size(156.dp),
        )
        Spacer(
            modifier = Modifier.padding(top = 24.dp),
        )
        LastUpdatedTimeWidget(model.lastUpdatedTime)
    }
}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@ExperimentalPagerApi
fun WeathersListPreview() {
    TodayWeatherScreen()
}