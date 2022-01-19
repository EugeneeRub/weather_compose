package com.griddynamics.weather_sample_app.feature.main.settings.temperature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griddynamics.weather_sample_app.R
import com.griddynamics.weather_sample_app.data.model.main.settings.TemperatureType
import com.griddynamics.weather_sample_app.data.model.main.settings.temperature.TemperatureModel
import com.griddynamics.weather_sample_app.feature.ui.theme.*
import com.griddynamics.weather_sample_app.feature.splash.util.seasonMainData

@ExperimentalMaterialApi
@Composable
fun TemperatureDialog(
    isShowDialog: Boolean,
    viewModel: TemperatureViewModel = viewModel(),
    setShowDialog: (Boolean) -> Unit,
    onTemperatureSelected: (TemperatureType) -> Unit
) {
    if (isShowDialog) {
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
                            TemperatureDialogItem(item) {
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
fun TemperatureDialogItem(model: TemperatureModel, onClick: (TemperatureType) -> Unit) {
    val iconColor =
        if (model.isSelected) seasonMainData.wave1Color else MaterialTheme.colors.textPrimary
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
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier.padding(8.dp)
        )
    }
}