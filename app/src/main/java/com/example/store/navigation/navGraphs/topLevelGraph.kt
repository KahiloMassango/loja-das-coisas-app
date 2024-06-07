package com.example.store.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.navigation.Screen
import com.example.store.presentation.screens.cart.CartScreen
import com.example.store.presentation.screens.home.HomeScreen
import com.example.store.presentation.screens.profile.ProfileScreen
import com.example.store.presentation.screens.shop.ShopScreen

fun NavGraphBuilder.topLevelGraph(
    navController: NavController
) {
    navigation<Screen.TopLevelGraph>(
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home> {
            HomeScreen(navController = navController)
        }
        composable<Screen.Cart> {
            CartScreen()
        }
        composable<Screen.Profile> {
            ProfileScreen(navController = navController)
        }
        composable<Screen.Shop> {
            ShopScreen(navController = navController)
        }
    }
}
