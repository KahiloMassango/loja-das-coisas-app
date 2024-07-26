package com.example.store.feature.product_listing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.store.feature.product_listing.ProductListing
import kotlinx.serialization.Serializable


@Serializable
data class ProductListingRoute(val title: String)

fun NavController.navigateToProductListing(title: String) = navigate(ProductListingRoute(title))

fun NavGraphBuilder.productListingScreen(
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<ProductListingRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<ProductListingRoute>()
        ProductListing(
            title = route.title,
            onProductClick = { onProductClick(it) },
            onNavigateUp = onNavigateUp
        )
    }
}