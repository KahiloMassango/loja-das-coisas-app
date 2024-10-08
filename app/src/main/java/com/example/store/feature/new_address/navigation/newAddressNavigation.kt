package com.example.store.feature.new_address.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.new_address.NewAddressScreen
import kotlinx.serialization.Serializable

@Serializable
object NewAddressRoute

fun NavController.navigateToNewAddressScreen() = navigate(NewAddressRoute)

fun NavGraphBuilder.newAddressScreen(
    onNavigateUp: () -> Unit,
) {
    composable<NewAddressRoute> {
        NewAddressScreen(
            onNavigateUp = onNavigateUp
        )
    }
}
