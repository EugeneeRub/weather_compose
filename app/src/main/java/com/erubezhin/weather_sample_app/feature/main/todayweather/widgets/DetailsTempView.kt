package com.erubezhin.weather_sample_app.feature.main.todayweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.feature.main.todayweather.TodayWeatherModel
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.iconTint
import com.erubezhin.weather_sample_app.feature.ui.theme.textPrimary
import com.erubezhin.weather_sample_app.feature.ui.theme.viewSelected

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsTempView(
    swipeState: SwipeableState<String>,
    model: TodayWeatherModel,
    seasonColors: SeasonColors,
    finishPx: Float,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = swipeState.offset.value / finishPx
                },
    ) {
        Row(
            modifier =
                Modifier
                    .height(92.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .background(MaterialTheme.colors.viewSelected, CircleShape)
                        .padding(8.dp),
            ) {
                WeatherIconTypeWidget(
                    model.weatherIconId,
                    modifier = Modifier.size(72.dp),
                )
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
            ) {
                TextWidget(
                    text = stringResource(R.string.terminology_feels_like),
                    textStyle =
                        TextStyle(
                            color = MaterialTheme.colors.textPrimary,
                            fontSize = 22.sp,
                        ),
                )
                TextWidget(
                    model.getTemperatureFeelsLikeString(),
                    textStyle =
                        TextStyle(
                            color = seasonColors.wavePrimary,
                            fontSize = 32.sp,
                        ),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Card(
                modifier =
                    Modifier
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
                        text = model.details.getSunriseTime(),
                        modifier = Modifier.padding(bottom = 4.dp),
                        textStyle =
                            TextStyle(
                                color = MaterialTheme.colors.textPrimary,
                                fontSize = 18.sp,
                            ),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sunrise),
                        modifier = Modifier.size(28.dp),
                        contentDescription = stringResource(R.string.terminology_sunrise),
                        tint = MaterialTheme.colors.iconTint,
                    )
                }
            }
            Card(
                modifier =
                    Modifier
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
                        text = model.details.getSunsetTime(),
                        modifier =
                            Modifier
                                .padding(bottom = 4.dp),
                        textStyle =
                            TextStyle(
                                color = MaterialTheme.colors.textPrimary,
                                fontSize = 18.sp,
                            ),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sunset),
                        modifier = Modifier.size(28.dp),
                        contentDescription = stringResource(R.string.terminology_sunset),
                        tint = MaterialTheme.colors.iconTint,
                    )
                }
            }
        }

        DetailsWeatherItem(
            modifier =
                Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth(),
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_min_max_temp),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
            TextWidget(
                text = model.details.getMinMaxTemperatureString(),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
        }
        DetailsWeatherItem(
            modifier =
                Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth(),
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_wind),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
            TextWidget(
                text = stringResource(id = R.string.terminology_km_hour_format, model.details.wind),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
        }
        DetailsWeatherItem(
            modifier =
                Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth(),
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_humidity),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
            TextWidget(
                text =
                    stringResource(
                        id = R.string.terminology_mbar_format,
                        model.details.humidity,
                    ),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
        }
        DetailsWeatherItem(
            modifier =
                Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth(),
        ) {
            TextWidget(
                text = stringResource(R.string.terminology_visibility),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
            TextWidget(
                text = stringResource(model.details.visibility.stringId),
                textStyle =
                    TextStyle(
                        color = MaterialTheme.colors.textPrimary,
                        fontSize = 18.sp,
                    ),
            )
        }
    }
}
