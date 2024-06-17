package com.example.store.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.navigation.Route
import com.example.store.presentation.screens.cart.CartScreen
import com.example.store.presentation.screens.home.HomeScreen
import com.example.store.presentation.screens.profile.ProfileScreen
import com.example.store.presentation.screens.shop.ShopScreen

fun NavGraphBuilder.topLevelGraph(
    navController: NavController
) {
    navigation<Route.TopLevelGraph>(
        startDestination = Route.Home,
    ) {
        composable<Route.Home> {
            HomeScreen(navController = navController)
        }
        composable<Route.Cart> {
            CartScreen()
        }
        composable<Route.Profile> {
            ProfileScreen(navController = navController)
        }
        composable<Route.Shop> {
            ShopScreen(navController = navController)
        }
    }
}
