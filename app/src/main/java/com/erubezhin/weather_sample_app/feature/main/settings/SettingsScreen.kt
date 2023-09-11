package com.erubezhin.weather_sample_app.feature.main.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.Screens
import com.erubezhin.weather_sample_app.core.extension.logScreenOpen
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType
import com.erubezhin.weather_sample_app.feature.main.settings.language.LanguageDialog
import com.erubezhin.weather_sample_app.feature.main.settings.temperature.TemperatureDialog
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.textPrimary
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import com.erubezhin.weather_sample_app.feature.ui.theme.viewSelected
import java.util.*
import com.erubezhin.weather_sample_app.R

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModel.factory(LocalContext.current.applicationContext),
    )
) {
    val context = LocalContext.current
    AppsFlyerLib.getInstance().logScreenOpen(context, Screens.Details)

    val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }
    val (isShowLanguageDialog, setLanguageDialog) = remember { mutableStateOf(false) }
    val (isShowTemperatureDialog, setTemperatureDialog) = remember { mutableStateOf(false) }


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
        ShowLanguageView(
            seasonColors = seasonColors,
            languageState = viewModel.selectedLanguage.observeAsState()
        ) {
            setLanguageDialog(true)
        }
        ShowTemperatureTypeView(
            seasonColors,
            viewModel.selectedTemperatureType.observeAsState(),
        ) {
            setTemperatureDialog(true)
        }
    }
    LanguageDialog(
        isShowLanguageDialog,
        setShowDialog = setLanguageDialog,
        onLanguageSelected = { language ->
            viewModel.updateLocale(context, language)
        }
    )
    TemperatureDialog(
        isShowTemperatureDialog,
        setShowDialog = setTemperatureDialog,
        onTemperatureSelected = { temperature ->
            viewModel.updateTemperatureType(context, temperature)
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun ShowLanguageView(
    seasonColors: SeasonColors,
    languageState: State<Language?>,
    onClick: () -> Unit,
) {
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
                    colorFilter = ColorFilter.tint(seasonColors.wavePrimary),
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
                    text = stringResource(id = R.string.language),
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
fun ShowTemperatureTypeView(
    seasonColors: SeasonColors,
    temperatureTypeState: State<TemperatureType?>,
    onClick: () -> Unit,
) {
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
                    colorFilter = ColorFilter.tint(seasonColors.wavePrimary),
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
                    text = stringResource(id = R.string.temperature),
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
