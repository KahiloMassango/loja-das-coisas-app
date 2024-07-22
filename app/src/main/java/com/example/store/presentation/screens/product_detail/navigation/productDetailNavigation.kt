package com.example.store.presentation.screens.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.product_detail.ProductDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailRoute(val productId: Int)

fun NavController.navigateToProductDetail(productId: Int) = navigate(ProductDetailRoute(productId))

fun NavGraphBuilder.productDetailScreen(
    onReviewsClick: (String) -> Unit,
    onNavigateUp: () -> Unit
    ) {
    composable<ProductDetailRoute> {
        ProductDetailsScreen(
            onReviewsClick = { onReviewsClick("it") },
            onNavigateUp = onNavigateUp
        )
    }
}