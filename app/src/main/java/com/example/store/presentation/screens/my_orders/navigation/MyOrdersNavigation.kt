package com.example.store.presentation.screens.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.my_orders.MyOrdersScreen
import com.example.store.presentation.screens.profile.navigation.ProfileRoute
import kotlinx.serialization.Serializable



@Serializable
data object MyOrdersRoute

fun NavController.navigateToMyOrders() = navigate(MyOrdersRoute)

fun NavGraphBuilder.myOrdersScreen(onNavigateUp: () -> Unit) {
    composable<MyOrdersRoute> {
        MyOrdersScreen(onNavigateUp = onNavigateUp)
    }
}