package com.example.store.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.store.core.ui.component.BottomNavigationBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.autentication.navigation.authentication
import com.example.store.feature.cart.navigation.cartScreen
import com.example.store.feature.home.navigation.HomeRoute
import com.example.store.feature.home.navigation.homeScreen
import com.example.store.feature.product_listing.navigation.navigateToProductListing
import com.example.store.feature.product_listing.navigation.productListingScreen
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.checkout.navigation.favoriteScreen
import com.example.store.presentation.screens.my_orders.navigation.myOrdersScreen
import com.example.store.presentation.screens.my_orders.navigation.navigateToMyOrders
import com.example.store.feature.order_detail.navigation.navigateToOrderDetail
import com.example.store.feature.order_detail.navigation.orderDetailScreen
import com.example.store.feature.favorite.navigation.checkoutScreen
import com.example.store.feature.favorite.navigation.navigateToCheckout
import com.example.store.feature.product_detail.navigation.navigateToProductDetail
import com.example.store.feature.product_detail.navigation.productDetailScreen
import com.example.store.presentation.screens.profile.navigation.profileScreen
import com.example.store.presentation.screens.reviews.navigation.navigateToReviews
import com.example.store.presentation.screens.reviews.navigation.reviewsScreen
import com.example.store.presentation.screens.settings.navigation.navigateToSettings
import com.example.store.presentation.screens.settings.navigation.settingsScreen
import com.example.store.presentation.screens.shop.navigation.shopScreen


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = HomeRoute
        ) {
            authentication(navController)

            homeScreen(
                onProductClick = { navController.navigateToProductDetail(it) },
                onSeeAll = { navController.navigateToProductListing(it) }
            )

            cartScreen(onCheckout = { navController.navigateToCheckout() })

            profileScreen(
                onMyOrdersClick = { navController.navigateToMyOrders() },
                onMyReviewsClick = { /* TODO */ },
                onSettingsClick = { navController.navigateToSettings() }
            )

            shopScreen(onProductClick = { navController.navigateToProductDetail(it) })

            favoriteScreen(onProductDetail = { navController.navigateToProductDetail(it) })

            productListingScreen(
                onProductClick = { navController.navigateToProductDetail(it) },
                onNavigateUp = navController::navigateUp
            )

            productDetailScreen(
                onReviewsClick = { navController.navigateToReviews(it) },
                onNavigateUp = navController::navigateUp,
                onSuggestedProductsClick = { navController.navigateToProductDetail(it) }
            )

            myOrdersScreen(
                onDetailClick = { navController.navigateToOrderDetail(0) },
                navController::navigateUp
            )

            orderDetailScreen(navController::navigateUp)

            settingsScreen(navController::navigateUp)

            checkoutScreen(onNavigateUp = navController::navigateUp)

            reviewsScreen(onNavigateUp = navController::navigateUp)
        }
        BottomNavigationBar(navController)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        NavigationGraph()
    }
}




