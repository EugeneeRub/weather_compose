package com.erubezhin.weather_sample_app.feature.main.hourlyweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.core.extension.Screens
import com.erubezhin.weather_sample_app.core.extension.logScreenOpen
import com.erubezhin.weather_sample_app.feature.common.WavesBackground
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import java.util.*

@Composable
fun HourlyWeatherScreen(
    viewModel: HourlyWeatherViewModel =
        viewModel(
            factory = HourlyWeatherViewModel.factory(LocalContext.current),
        ),
) {
    AppsFlyerLib.getInstance().logScreenOpen(LocalContext.current, Screens.Hourly)
    val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

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
            Spacer(modifier = Modifier.padding(top = 64.dp))
            Text(
                text = stringResource(id = R.string.menu_item_hourly_weather),
                color = MaterialTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp,
            )
        }
    }
}
