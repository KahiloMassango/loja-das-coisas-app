package com.example.store.presentation.screens.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.shop.ShopScreen
import kotlinx.serialization.Serializable


@Serializable
data object ShopRoute

fun NavController.navigateToShop() = navigate(ShopRoute)

fun NavGraphBuilder.shopScreen(onNavigateUp: () -> Unit) {
    composable<ShopRoute> {
        ShopScreen(onNavigateUp = onNavigateUp)
    }
}