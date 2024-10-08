package com.example.store.feature.delivery_address.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.delivery_address.DeliveryAddressScreen
import kotlinx.serialization.Serializable


@Serializable
object DeliveryAddressesRoute

fun NavController.navigateToAddresses() = navigate(DeliveryAddressesRoute)

fun NavGraphBuilder.deliveryAddressesScreen(
    onAddNewAddress: () -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<DeliveryAddressesRoute> {
        DeliveryAddressScreen(
            onAddNewAddress = onAddNewAddress,
            onNavigateUp = onNavigateUp
        )
    }
}