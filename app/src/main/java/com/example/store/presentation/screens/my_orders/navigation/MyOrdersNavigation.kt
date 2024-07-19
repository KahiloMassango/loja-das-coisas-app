package com.example.store.presentation.screens.my_orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.my_orders.MyOrdersScreen
import kotlinx.serialization.Serializable



@Serializable
data object MyOrdersRoute

fun NavController.navigateToMyOrders() = navigate(MyOrdersRoute)

fun NavGraphBuilder.myOrdersScreen(
    onDetailClick: () -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<MyOrdersRoute> {
        MyOrdersScreen(
            onDetailClick = onDetailClick,
            onNavigateUp = onNavigateUp
        )
    }
}