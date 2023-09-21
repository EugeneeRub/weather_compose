package com.erubezhin.weather_sample_app.feature.main.todayweather.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erubezhin.weather_sample_app.feature.main.todayweather.TodayWeatherModel
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary

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
        modifier =
            Modifier
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
            textStyle =
                TextStyle(
                    color = MaterialTheme.colors.textSecondary,
                    fontSize = 32.sp,
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
