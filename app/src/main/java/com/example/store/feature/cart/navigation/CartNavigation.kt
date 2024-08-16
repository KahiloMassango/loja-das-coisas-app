package com.example.store.feature.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.cart.CartScreen
import kotlinx.serialization.Serializable


@Serializable
data object CartRoute

fun NavController.navigateToCart() = navigate(CartRoute)

fun NavGraphBuilder.cartScreen(
    onProductClick: (String) -> Unit,
    onCheckout: () -> Unit
) {
    composable<CartRoute> {
        CartScreen(
            onProductClick = onProductClick,
            onCheckout = onCheckout
        )
    }
}