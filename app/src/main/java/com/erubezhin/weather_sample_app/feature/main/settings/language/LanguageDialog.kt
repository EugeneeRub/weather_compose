package com.erubezhin.weather_sample_app.feature.main.settings.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsflyer.AppsFlyerLib
import com.erubezhin.weather_sample_app.core.extension.Dialogs
import com.erubezhin.weather_sample_app.core.extension.logDialogOpen
import com.erubezhin.weather_sample_app.data.model.main.settings.Language
import com.erubezhin.weather_sample_app.data.model.main.settings.language.LanguageModel
import com.erubezhin.weather_sample_app.feature.ui.theme.*
import com.erubezhin.weather_sample_app.R
import java.util.*

@ExperimentalMaterialApi
@Composable
fun LanguageDialog(
    isShowDialog: Boolean,
    viewModel: LanguageViewModel = viewModel(
        factory = LanguageViewModel.factory(LocalContext.current.applicationContext),
    ),
    setShowDialog: (Boolean) -> Unit,
    onLanguageSelected: (Language) -> Unit
) {
    if (isShowDialog) {
        AppsFlyerLib.getInstance().logDialogOpen(LocalContext.current, Dialogs.Language)
        val seasonColors = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }
        val context = LocalContext.current

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
                        LanguageDialogItem(
                            seasonColors = seasonColors,
                            model = item,
                        ) {
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
fun LanguageDialogItem(
    seasonColors: SeasonColors,
    model: LanguageModel,
    onClick: (Language) -> Unit,
) {
    val textColor =
        if (model.isSelected) seasonColors.wavePrimary else MaterialTheme.colors.textPrimary
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
                    colorFilter = ColorFilter.tint(seasonColors.wavePrimary),
                    contentDescription = "Icon selected"
                )
            }
        }
    }
}