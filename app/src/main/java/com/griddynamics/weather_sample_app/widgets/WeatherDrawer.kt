package com.griddynamics.weather_sample_app.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.griddynamics.weather_sample_app.ui.theme.textSecondary
import com.griddynamics.weather_sample_app.ui.theme.viewSelected
import com.griddynamics.weather_sample_app.utils.seasonalMainData
import kotlinx.coroutines.CoroutineScope

@Composable
fun WeatherDrawer(modifier: Modifier, scope: CoroutineScope, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Settings,
    )
    Column(
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier.weight(0.5f)
        )
        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                WeatherDrawerItem(item, item.route == currentRoute) {
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
                .fillMaxWidth()
                .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Developed by",
                color = MaterialTheme.colors.textSecondary,
                fontSize = 14.sp
            )
            Text(
                "Rubezhin Evgenij",
                color = MaterialTheme.colors.textSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun WeatherDrawerItem(
    item: NavDrawerItem, selected: Boolean, onItemClick: (NavDrawerItem) -> Unit
) {
    val seasonWave2Color = seasonalMainData.wave2Color
    val backgroundCardColor =
        if (selected) MaterialTheme.colors.viewSelected else MaterialTheme.colors.background
    val textColor = if (selected) seasonWave2Color else MaterialTheme.colors.textSecondary

    Card(
        backgroundColor = backgroundCardColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                onItemClick(item)
            },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = item.icon,
                contentDescription = item.title,
                colorFilter = ColorFilter.tint(textColor),
                modifier = Modifier
                    .size(42.dp)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = textColor
            )
        }
    }
}

sealed class NavDrawerItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavDrawerItem("home_page", Icons.Rounded.Home, "Home")
    object Settings : NavDrawerItem("settings_page", Icons.Rounded.Settings, "Settings")
}

@Preview(
    showBackground = false,
    name = "Weather drawer item"
)
@Composable
fun DrawerItemPreview() {
    WeatherDrawerItem(item = NavDrawerItem.Home, selected = false, onItemClick = {})
}