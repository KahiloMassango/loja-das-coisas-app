package com.example.store.feature.select_delivery_location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.select_delivery_location.SelectDeliveryLocationScreen
import kotlinx.serialization.Serializable

@Serializable
object SelectDeliveryLocationRoute

fun NavController.navigateToSelectDeliveryLocationScreen() = navigate(SelectDeliveryLocationRoute)

fun NavGraphBuilder.selectDeliveryLocationScreen(
    onNavigateUp: () -> Unit,
) {
    composable<SelectDeliveryLocationRoute> {
        SelectDeliveryLocationScreen(
            onNavigateUp = onNavigateUp
        )
    }
}
