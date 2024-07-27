package com.example.store.feature.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.product_detail.ProductDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailRoute(val productId: String)

fun NavController.navigateToProductDetail(productId: String) = navigate(ProductDetailRoute(productId))

fun NavGraphBuilder.productDetailScreen(
    onReviewsClick: (String) -> Unit,
    onSuggestedProductsClick: (String) -> Unit,
    onNavigateUp: () -> Unit
    ) {
    composable<ProductDetailRoute> {
        ProductDetailsScreen(
            onReviewsClick = { onReviewsClick(it) },
            onNavigateUp = onNavigateUp,
            onSuggestedProductsClick = { onSuggestedProductsClick(it) }
        )
    }
}