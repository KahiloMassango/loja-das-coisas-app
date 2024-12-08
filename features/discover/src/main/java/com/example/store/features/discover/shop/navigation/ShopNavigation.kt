package com.example.store.features.discover.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.store.features.discover.shop.ShopScreen
import kotlinx.serialization.Serializable


@Serializable
internal data class ShopRoute(val category: String, val subcategory: String)

fun NavController.navigateToShop(
    category: String, subcategory: String
) = navigate(ShopRoute(category, subcategory))

internal fun NavGraphBuilder.shopScreen(
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