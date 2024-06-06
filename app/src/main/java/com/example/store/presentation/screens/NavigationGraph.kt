package com.example.store.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.navigation.BottomNavigationBar
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.navigation.Screen
import com.example.store.presentation.navigation.navGraphs.authNavGraph
import com.example.store.presentation.navigation.navGraphs.topLevelGraph
import com.example.store.presentation.screens.product_detail.ProductDetailsScreen
import com.example.store.presentation.screens.settings.SettingsScreen
import com.example.store.ui.theme.StoreTheme


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = Screen.TopLevelGraph
        ) {
            authNavGraph(navController)
            topLevelGraph(navController)

            composable<Screen.Settings> {
                SettingsScreen(navController)
            }

            composable<Screen.ProductDetail> {
                ProductDetailsScreen(navController,)
            }

        }
        BottomNavigationBar(navController)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        NavigationGraph()
    }
}




