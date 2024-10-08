package com.example.store.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.store.core.ui.component.BottomNavigationBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.autentication.navigation.authentication
import com.example.store.feature.cart.navigation.cartScreen
import com.example.store.feature.category.navigation.categoryScreen
import com.example.store.feature.checkout.navigation.checkoutScreen
import com.example.store.feature.checkout.navigation.navigateToCheckout
import com.example.store.feature.delivery_address.navigation.deliveryAddressesScreen
import com.example.store.feature.delivery_address.navigation.navigateToAddresses
import com.example.store.feature.favorite.navigation.favoriteScreen
import com.example.store.feature.help_center.navigation.helpCenterScreen
import com.example.store.feature.help_center.navigation.navigateToHelpCenter
import com.example.store.feature.home.navigation.HomeRoute
import com.example.store.feature.home.navigation.homeScreen
import com.example.store.feature.my_orders.navigation.myOrdersScreen
import com.example.store.feature.my_orders.navigation.navigateToMyOrders
import com.example.store.feature.new_address.navigation.navigateToNewAddressScreen
import com.example.store.feature.order_detail.navigation.navigateToOrderDetail
import com.example.store.feature.order_detail.navigation.orderDetailScreen
import com.example.store.feature.police_privacy.navigation.navigateToPolicePrivacy
import com.example.store.feature.police_privacy.navigation.policePrivacyScreen
import com.example.store.feature.product_detail.navigation.navigateToProductDetail
import com.example.store.feature.product_detail.navigation.productDetailScreen
import com.example.store.feature.product_listing.navigation.navigateToProductListing
import com.example.store.feature.product_listing.navigation.productListingScreen
import com.example.store.feature.profile.navigation.profileScreen
import com.example.store.feature.reviews.navigation.navigateToReviews
import com.example.store.feature.reviews.navigation.reviewsScreen
import com.example.store.feature.search.navigation.navigateToSearch
import com.example.store.feature.search.navigation.searchScreen
import com.example.store.feature.new_address.navigation.newAddressScreen
import com.example.store.feature.settings.navigation.navigateToSettings
import com.example.store.feature.settings.navigation.settingsScreen
import com.example.store.feature.shop.navigation.navigateToShop
import com.example.store.feature.shop.navigation.shopScreen


@Composable
fun  NavigationGraph(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val cartItemsCount by viewModel.cartCount.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
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

            cartScreen(
                onProductClick = { navController.navigateToProductDetail(it) },
                onCheckout = { navController.navigateToCheckout() }
            )

            profileScreen(
                onMyOrdersClick = { navController.navigateToMyOrders() },
                onMyReviewsClick = { /* TODO */ },
                onSettingsClick = { navController.navigateToSettings() },
                onHelpCenterClick = { navController.navigateToHelpCenter() },
                onPolicePrivacyClick = { navController.navigateToPolicePrivacy() },
                onAddressesClick = { navController.navigateToAddresses() }
            )

            helpCenterScreen(
                onNavigationUp = navController::navigateUp
            )

            shopScreen(
                onSearch = { navController.navigateToSearch() },
                onProductClick = { navController.navigateToProductDetail(it) },
                onNavigateUp = navController::navigateUp
            )

            categoryScreen(
                onSearch = { navController.navigateToSearch() },
                onSelectCategory = { section, category ->
                    navController.navigateToShop(section, category)
                }
            )

            deliveryAddressesScreen(
                onAddNewAddress = { navController.navigateToNewAddressScreen() },
                onNavigateUp = navController::navigateUp
            )

            searchScreen(
                onNavigateUp = navController::navigateUp,
                onProductClick = { navController.navigateToProductDetail(it) }
            )

            favoriteScreen(onProductDetail = { navController.navigateToProductDetail(it) })

            productListingScreen(
                onProductClick = { navController.navigateToProductDetail(it) },
                onNavigateUp = navController::navigateUp
            )

            policePrivacyScreen(
                onNavigationUp = navController::navigateUp
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

            checkoutScreen(
                onNavigateUp = navController::navigateUp,
                onChangeDeliveryLocation = {  },
                onAddAddress = { navController.navigateToAddresses() }
            )

            newAddressScreen(onNavigateUp = navController::navigateUp)

            reviewsScreen(onNavigateUp = navController::navigateUp)
        }
        BottomNavigationBar(navController = navController, cartItemsCount = cartItemsCount)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        NavigationGraph()
    }
}




