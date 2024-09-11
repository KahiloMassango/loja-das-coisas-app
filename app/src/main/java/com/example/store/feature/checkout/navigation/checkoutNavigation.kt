package com.example.store.feature.checkout.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.checkout.CheckoutScreen
import com.example.store.feature.checkout.location.SelectDeliveryLocationScreen
import kotlinx.serialization.Serializable


@Serializable
data object CheckoutRoute

@Serializable
data object LocationPickerRoute

fun NavController.navigateToCheckout() = navigate(CheckoutRoute)

fun NavController.navigateToPickerLocation() = navigate(LocationPickerRoute)

fun NavGraphBuilder.checkoutScreen(
    onNavigateUp: () -> Unit,
    onChangeAddress: () -> Unit,
) {
    composable<CheckoutRoute> {
        CheckoutScreen(
            onNavigateUp = onNavigateUp,
            onChangeAddress = onChangeAddress
        )
    }
}

fun NavGraphBuilder.selectDeliveryLocationScreen(
    onNavigateUp: () -> Unit,
) {

    composable<LocationPickerRoute> {
        SelectDeliveryLocationScreen(
            onNavigateUp = onNavigateUp
        )
    }
}