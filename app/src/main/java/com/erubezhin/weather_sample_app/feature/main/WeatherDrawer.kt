package com.erubezhin.weather_sample_app.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erubezhin.weather_sample_app.feature.ui.theme.SeasonColors
import com.erubezhin.weather_sample_app.feature.ui.theme.lombokFontFamily
import com.erubezhin.weather_sample_app.feature.ui.theme.textSecondary
import com.erubezhin.weather_sample_app.feature.ui.theme.viewSelected
import java.util.*
import com.erubezhin.weather_sample_app.R

@Composable
@ExperimentalMaterialApi
fun WeatherDrawer(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val seasonColorsData = remember { SeasonColors.getSeasonColors(Calendar.getInstance()) }
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Settings,
    )

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (header, mainContent, devContent) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(header) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)

                    width = Dimension.fillToConstraints
                    height = Dimension.value(120.dp)
                }
                .padding(top = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "CW",
                fontFamily = lombokFontFamily,
                fontSize = 44.sp,
            )
        }

        Column(
            modifier = Modifier
                .constrainAs(mainContent) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(devContent.top)

                    // works like:
                    // android:layout_width="0dp"
                    // android:layout_height="0dp"
                    //
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                WeatherDrawerItem(
                    item,
                    seasonColorsData,
                    selected = item.route == currentRoute,
                ) {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .constrainAs(devContent) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.fillToConstraints
                    height = Dimension.value(120.dp)
                }
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.developed_by),
                color = MaterialTheme.colors.textSecondary,
                fontSize = 14.sp
            )
            Text(
                stringResource(id = R.string.evgenij_rubezhin),
                color = MaterialTheme.colors.textSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun WeatherDrawerItem(
    item: NavDrawerItem,
    seasonColorsData: SeasonColors,
    selected: Boolean,
    onItemClick: (NavDrawerItem) -> Unit
) {
    val backgroundCardColor =
        if (selected) MaterialTheme.colors.viewSelected else MaterialTheme.colors.background
    val textColor =
        if (selected) seasonColorsData.textColor else MaterialTheme.colors.textSecondary

    Card(
        backgroundColor = backgroundCardColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        onClick = {
            onItemClick(item)
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = item.icon,
                contentDescription = stringResource(id = item.titleRes),
                colorFilter = ColorFilter.tint(textColor),
                modifier = Modifier
                    .size(42.dp)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = item.titleRes),
                fontSize = 20.sp,
                color = textColor
            )
        }
    }
}

/** Holds information of navigation paths in [MainScreen]. */
sealed class NavDrawerItem(val route: String, val icon: ImageVector, val titleRes: Int) {
    object Home : NavDrawerItem("home_page", Icons.Rounded.Home, R.string.home)
    object Settings : NavDrawerItem("settings_page", Icons.Rounded.Settings, R.string.settings)
}

@Preview(name = "Weather drawer")
@Composable
@ExperimentalMaterialApi
fun DrawerPreview() {
    WeatherDrawer(navController = rememberNavController())
}

@Preview(name = "Drawer item - Winter style")
@Composable
@ExperimentalMaterialApi
fun DrawerItemWinterPreview() {
    WeatherDrawerItem(NavDrawerItem.Home, SeasonColors.WinterColors, selected = true) {}
}

@Preview(name = "Drawer item - Spring style")
@Composable
@ExperimentalMaterialApi
fun DrawerItemSpringPreview() {
    WeatherDrawerItem(NavDrawerItem.Home, SeasonColors.SpringColors, selected = true) {}
}

@Preview(name = "Drawer item - Summer style")
@Composable
@ExperimentalMaterialApi
fun DrawerItemSummerPreview() {
    WeatherDrawerItem(NavDrawerItem.Home, SeasonColors.SummerColors, selected = true) {}
}

@Preview(name = "Drawer item - Autumn style")
@Composable
@ExperimentalMaterialApi
fun DrawerItemAutumnPreview() {
    WeatherDrawerItem(NavDrawerItem.Home, SeasonColors.AutumnColors, selected = true) {}
}