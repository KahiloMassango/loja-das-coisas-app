package com.example.store.features.userprofile.orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.orders.MyOrdersScreen
import kotlinx.serialization.Serializable


@Serializable
internal data object MyOrdersRoute

fun NavController.navigateToMyOrders() = navigate(MyOrdersRoute)

internal fun NavGraphBuilder.myOrdersScreen(
    onOrderClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<MyOrdersRoute> {
        MyOrdersScreen(
            onDetailClick = onOrderClick,
            onNavigateUp = onNavigateUp
        )
    }
}