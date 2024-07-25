package com.example.store.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.store.navigation.BottomNavigationBar
import com.example.store.navigation.navGraphs.TopLevelRoute
import com.example.store.navigation.navGraphs.authentication
import com.example.store.navigation.navGraphs.topLevelGraph
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.my_orders.navigation.myOrdersScreen
import com.example.store.presentation.screens.order_detail.navigation.navigateToOrderDetail
import com.example.store.presentation.screens.order_detail.navigation.orderDetailScreen
import com.example.store.presentation.screens.product_detail.navigation.checkoutScreen
import com.example.store.presentation.screens.product_detail.navigation.navigateToProductDetail
import com.example.store.presentation.screens.product_detail.navigation.productDetailScreen
import com.example.store.presentation.screens.product_listing.navigation.productListingScreen
import com.example.store.presentation.screens.reviews.navigation.navigateToReviews
import com.example.store.presentation.screens.reviews.navigation.reviewsScreen
import com.example.store.presentation.screens.settings.navigation.settingsScreen
import com.example.store.ui.theme.StoreTheme


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
            startDestination = TopLevelRoute
        ) {
            authentication(navController)

            topLevelGraph(navController)

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




