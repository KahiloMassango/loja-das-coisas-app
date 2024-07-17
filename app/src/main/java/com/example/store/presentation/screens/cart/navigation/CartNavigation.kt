package com.example.store.presentation.screens.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.cart.CartScreen
import kotlinx.serialization.Serializable


@Serializable
data object CartRoute

fun NavController.navigateToCart() = navigate(CartRoute)

fun NavGraphBuilder.cartScreen(onCheckout: () -> Unit) {
    composable<CartRoute> {
        CartScreen(onCheckout = onCheckout)
    }
}