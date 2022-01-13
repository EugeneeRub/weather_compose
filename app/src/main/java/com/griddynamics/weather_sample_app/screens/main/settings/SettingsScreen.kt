package com.griddynamics.weather_sample_app.screens.main.settings

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griddynamics.weather_sample_app.R
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.TemperatureType
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme
import com.griddynamics.weather_sample_app.ui.theme.textPrimary
import com.griddynamics.weather_sample_app.ui.theme.textSecondary
import com.griddynamics.weather_sample_app.ui.theme.viewSelected
import com.griddynamics.weather_sample_app.utils.seasonMainData

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = viewModel()) {
    WeatherComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            Spacer(modifier = Modifier.padding(top = 64.dp))
            Text(
                text = stringResource(id = R.string.settings),
                color = MaterialTheme.colors.textSecondary,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(top = 64.dp))
            ShowLanguageView(viewModel.selectedLanguage.observeAsState()) {

            }
            ShowTemperatureTypeView(viewModel.selectedTemperatureType.observeAsState()) {

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ShowLanguageView(languageState: State<Language?>, onClick: () -> Unit) {
    val language = if (languageState.value != null) languageState.value!! else return

    Card(
        backgroundColor = MaterialTheme.colors.viewSelected,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_translate),
                    contentDescription = "Icon language - ${language.title}",
                    colorFilter = ColorFilter.tint(seasonMainData.wave1Color),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Language",
                    color = MaterialTheme.colors.textPrimary
                )
                Text(
                    text = language.title,
                    color = MaterialTheme.colors.textSecondary
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ShowTemperatureTypeView(temperatureTypeState: State<TemperatureType?>, onClick: () -> Unit) {
    val temperatureType =
        if (temperatureTypeState.value != null) temperatureTypeState.value!! else return

    Card(
        backgroundColor = MaterialTheme.colors.viewSelected,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_temperature),
                    contentDescription = "Icon temperature type",
                    colorFilter = ColorFilter.tint(seasonMainData.wave1Color),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Temperature",
                    color = MaterialTheme.colors.textPrimary
                )
                Image(
                    painter = painterResource(id = temperatureType.icon),
                    contentDescription = "Temperature type",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.textSecondary),
                    modifier = Modifier
                        .padding(8.dp)
                )

            }
        }
    }
}
