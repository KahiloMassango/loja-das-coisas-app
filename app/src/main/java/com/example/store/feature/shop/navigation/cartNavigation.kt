package com.example.store.feature.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.shop.ShopScreen
import kotlinx.serialization.Serializable


@Serializable
data object ShopRoute

fun NavController.navigateToShop() = navigate(ShopRoute)

fun NavGraphBuilder.shopScreen(
    onSearch: () -> Unit,
    onProductClick: (String) -> Unit
) {
    composable<ShopRoute> {
        ShopScreen(
            onSearch = onSearch,
            onProductClick = { onProductClick(it) }
        )
    }
}