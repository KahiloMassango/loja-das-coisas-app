package com.example.store.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.presentation.screens.home.navigation.HomeRoute
import com.example.store.presentation.screens.cart.navigation.cartScreen
import com.example.store.presentation.screens.checkout.navigation.favoriteScreen
import com.example.store.presentation.screens.home.navigation.homeScreen
import com.example.store.presentation.screens.product_detail.navigation.navigateToCheckout
import com.example.store.presentation.screens.product_detail.navigation.navigateToMyOrders
import com.example.store.presentation.screens.product_detail.navigation.navigateToProductDetail
import com.example.store.presentation.screens.product_listing.navigation.navigateToProductListing
import com.example.store.presentation.screens.settings.navigation.navigateToSettings
import com.example.store.presentation.screens.profile.navigation.profileScreen
import com.example.store.presentation.screens.shop.navigation.shopScreen
import kotlinx.serialization.Serializable

@Serializable
data object TopLevelRoute

fun NavGraphBuilder.topLevelGraph(
    navController: NavController
) {
    navigation<TopLevelRoute>(
        startDestination = HomeRoute,
    ) {
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

    }
}
