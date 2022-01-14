package com.griddynamics.weather_sample_app.screens.main.settings.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griddynamics.weather_sample_app.R
import com.griddynamics.weather_sample_app.screens.data.model.settings.Language
import com.griddynamics.weather_sample_app.screens.data.model.settings.language.LanguageModel
import com.griddynamics.weather_sample_app.ui.theme.*
import com.griddynamics.weather_sample_app.utils.seasonMainData

@ExperimentalMaterialApi
@Composable
fun LanguageDialog(
    isShowDialog: Boolean,
    viewModel: LanguageViewModel = viewModel(),
    setShowDialog: (Boolean) -> Unit,
    onLanguageSelected: (Language) -> Unit
) {
    val context = LocalContext.current

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
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        stringResource(id = R.string.choose_language),
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.textPrimary,
                        fontFamily = moonFontFamily
                    )
                    viewModel.languages.observeAsState().value?.forEach { item ->
                        LanguageDialogItem(item) {
                            viewModel.updateLanguage(context, it)
                            onLanguageSelected(it)
                            setShowDialog(false)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun LanguageDialogItem(model: LanguageModel, onClick: (Language) -> Unit) {
    val selectedColor = seasonMainData.wave1Color
    val textColor = if (model.isSelected) selectedColor else MaterialTheme.colors.textPrimary
    Card(
        backgroundColor = MaterialTheme.colors.viewSelected,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        onClick = {
            onClick(model.locale)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = model.locale.title,
                color = textColor,
            )
            if (model.isSelected) {
                Image(
                    imageVector = Icons.Rounded.Done,
                    colorFilter = ColorFilter.tint(selectedColor),
                    contentDescription = "Icon selected"
                )
            }
        }
    }
}