package com.example.store.feature.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.store.feature.shop.ShopScreen
import kotlinx.serialization.Serializable


@Serializable
data class ShopRoute(val category: String, val subcategory: String)

fun NavController.navigateToShop(
    category: String, subcategory: String
) = navigate(ShopRoute(category, subcategory))

fun NavGraphBuilder.shopScreen(
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<ShopRoute> { backStackEntry  ->
        val shop: ShopRoute = backStackEntry.toRoute()
        ShopScreen(
            section = shop.category,
            onProductClick = { onProductClick(it) },
            onNavigateUp = onNavigateUp
        )
    }
}