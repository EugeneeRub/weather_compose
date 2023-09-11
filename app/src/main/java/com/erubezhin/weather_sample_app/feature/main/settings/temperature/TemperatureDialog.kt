package com.erubezhin.weather_sample_app.feature.main.settings.temperature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.Dialogs
import com.erubezhin.weather_sample_app.core.extension.logDialogOpen
import com.erubezhin.weather_sample_app.data.model.main.settings.TemperatureType
import com.erubezhin.weather_sample_app.data.model.main.settings.temperature.TemperatureModel
import com.erubezhin.weather_sample_app.feature.ui.theme.*
import java.util.*
import com.erubezhin.weather_sample_app.R

@ExperimentalMaterialApi
@Composable
fun TemperatureDialog(
    isShowDialog: Boolean,
    viewModel: TemperatureViewModel = viewModel(
        factory = TemperatureViewModel.factory(LocalContext.current.applicationContext),
        ),
    setShowDialog: (Boolean) -> Unit,
    onTemperatureSelected: (TemperatureType) -> Unit
) {
    if (isShowDialog) {
        AppsFlyerLib.getInstance().logDialogOpen(LocalContext.current, Dialogs.Temperature)
        val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }

        Dialog(
            onDismissRequest = {
                setShowDialog(false)
            }
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.background,
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        stringResource(id = R.string.choose_temperature),
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.textPrimary,
                        fontFamily = moonFontFamily
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        viewModel.temperatures.observeAsState().value?.forEach { item ->
                            TemperatureDialogItem(seasonColors, item) {
                                viewModel.updateTemperatureType(it)
                                onTemperatureSelected(it)
                                setShowDialog(false)
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TemperatureDialogItem(
    seasonColors: SeasonColors,
    model: TemperatureModel,
    onClick: (TemperatureType) -> Unit,
) {
    val tintColor =
        if (model.isSelected) seasonColors.wavePrimary else MaterialTheme.colors.textPrimary
    Card(
        backgroundColor = MaterialTheme.colors.viewSelected,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp),
        onClick = {
            onClick(model.temperature)
        }
    ) {
        Image(
            painter = painterResource(id = model.temperature.icon),
            contentDescription = "Temperature type icon",
            colorFilter = ColorFilter.tint(tintColor),
            modifier = Modifier.padding(8.dp)
        )
    }
}