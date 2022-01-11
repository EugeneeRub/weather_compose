package com.griddynamics.weather_sample_app.screens.wearherList

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.griddynamics.weather_sample_app.R
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.griddynamics.weather_sample_app.ui.theme.WeatherComposeTheme

@Composable
fun WeathersListScreen() {
    val buttonMenuClicked = remember { mutableStateOf(false) }

    WeatherComposeTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val (btnMenu, wave1, wave2) = createRefs()

            IconButton(
                modifier = Modifier
                    .padding(end = 16.dp, top = 32.dp)
                    .constrainAs(btnMenu) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    },
                onClick = {
                    buttonMenuClicked.value = true
                }) {
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = "Hamburger menu",
                    tint = MaterialTheme.colors.primary
                )
            }

            Image(
                painter = painterResource(id = R.drawable.background_wave_1),
                contentDescription = "Background wave 1",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(160f)
                    .constrainAs(wave1) {
                        bottom.linkTo(parent.bottom)
                    },
                contentScale = ContentScale.Crop,
//                colorFilter = ColorFilter.tint(Color.Red)
            )
            Image(
                painter = painterResource(id = R.drawable.background_wave_2),
                contentDescription = "Background wave 2",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(160f)
                    .constrainAs(wave2) {
                        bottom.linkTo(parent.bottom)
                    },
                contentScale = ContentScale.Crop,
//                colorFilter = ColorFilter.tint(Color.Yellow)
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WeathersListPreview() {
    WeathersListScreen()
}