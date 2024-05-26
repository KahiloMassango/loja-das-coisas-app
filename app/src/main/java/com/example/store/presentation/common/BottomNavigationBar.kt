package com.example.store.presentation.common

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.store.presentation.navigation.TOP_LEVEL_DESTINATIONS


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedPage by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        modifier = modifier
            .shadow(10.dp, shape = MaterialTheme.shapes.large, clip = true)
            .background(MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.large),
        containerColor = Color.Transparent,
        //tonalElevation = 5.dp,
    ) {
        TOP_LEVEL_DESTINATIONS.forEachIndexed { id, destination ->
            val iconRes = if (id == selectedPage) destination.selectedIcon else destination.unselectedIcon
            NavigationBarItem(
                selected = id == selectedPage,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedPage = id
                },
                label = {
                    Text(
                        text = destination.route,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = destination.route
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            )
        }
    }
}



