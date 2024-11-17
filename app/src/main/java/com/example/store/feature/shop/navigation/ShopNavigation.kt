package com.example.store.feature.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.store.feature.shop.ShopScreen
import kotlinx.serialization.Serializable


@Serializable
data class ShopRoute(val section: String, val category: String)

fun NavController.navigateToShop(
    section: String, category: String
) = navigate(ShopRoute(section, category))

fun NavGraphBuilder.shopScreen(
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<ShopRoute> { backStackEntry  ->
        val shop: ShopRoute = backStackEntry.toRoute()
        ShopScreen(
            section = shop.section,
            onProductClick = { onProductClick(it) },
            onNavigateUp = onNavigateUp
        )
    }
}