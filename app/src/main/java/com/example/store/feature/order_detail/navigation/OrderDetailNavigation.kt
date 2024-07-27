package com.example.store.feature.order_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.order_detail.OrderDetailScreen
import kotlinx.serialization.Serializable


@Serializable
data class OrderDetailRoute(val orderId: Int)

fun NavController.navigateToOrderDetail(orderId: Int) = navigate(OrderDetailRoute(orderId))

fun NavGraphBuilder.orderDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<OrderDetailRoute>() {
        OrderDetailScreen(
            onNavigateUp = onNavigateUp
        )

    }
}