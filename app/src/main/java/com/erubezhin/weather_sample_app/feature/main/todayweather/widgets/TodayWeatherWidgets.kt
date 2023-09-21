package com.erubezhin.weather_sample_app.feature.main.todayweather.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erubezhin.weather_sample_app.R
import com.erubezhin.weather_sample_app.feature.ui.theme.iconTint
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import com.erubezhin.weather_sample_app.feature.ui.theme.viewSelected

@Composable
fun LastUpdatedTimeWidget(timeState: String) {
    Text(
        text = stringResource(id = R.string.last_updated),
        style =
            TextStyle(
                color = MaterialTheme.colors.textSecondary,
                fontSize = 16.sp,
            ),
    )
    Text(
        text = timeState,
        modifier = Modifier.padding(top = 4.dp),
        style =
            TextStyle(
                color = MaterialTheme.colors.textSecondary,
                fontSize = 32.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.sp,
            ),
    )
}

@Composable
fun DayWidget(day: String) {
    Text(
        text = day,
        color = MaterialTheme.colors.textSecondary,
        fontSize = 22.sp,
    )
}

@Composable
fun TemperatureWidget(
    temperature: String,
    textColor: Color,
) {
    TextWidget(
        text = temperature,
        textStyle =
            TextStyle(
                color = textColor,
                fontSize = if (temperature.length > 2) 128.sp else 156.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.sp,
            ),
    )
}

@Composable
fun TextStateWidget(
    text: State<String>,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.value,
        style = textStyle,
        modifier = modifier,
    )
}

@Composable
fun TextWidget(
    text: String,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = textStyle,
        modifier = modifier,
    )
}

@Composable
fun WeatherIconTypeWidget(
    drawableId: Int?,
    modifier: Modifier = Modifier,
) {
    if (drawableId == null) return
    Icon(
        painter = painterResource(id = drawableId),
        modifier = modifier,
        contentDescription = "Weather icon type",
        tint = MaterialTheme.colors.iconTint,
    )
}

@Composable
fun DetailsWeatherItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.viewSelected,
        elevation = 0.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            content()
        }
    }
}

@Composable
fun ErrorToast(error: Throwable?) {
    if (error != null) {
        Toast.makeText(LocalContext.current, error.message, Toast.LENGTH_SHORT).show()
    }
}
