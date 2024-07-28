package com.example.store.feature.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.checkout.CheckoutScreen
import kotlinx.serialization.Serializable


@Serializable
data object CheckoutRoute

fun NavController.navigateToCheckout() = navigate(CheckoutRoute)

fun NavGraphBuilder.checkoutScreen(onNavigateUp: () -> Unit) {
    composable<CheckoutRoute> {
        CheckoutScreen(onNavigateUp = onNavigateUp)
    }
}