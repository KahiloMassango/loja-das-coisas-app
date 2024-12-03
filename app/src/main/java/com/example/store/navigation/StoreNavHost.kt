package com.example.store.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.store.R
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.navigation.BottomNavigationBar
import com.example.store.core.ui.navigation.StoreNavigationRail
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.autentication.navigation.authentication
import com.example.store.feature.cart.navigation.cartScreen
import com.example.store.feature.discover.navigation.discoverScreen
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
import com.example.store.feature.new_address.navigation.newAddressScreen
import com.example.store.feature.order_detail.navigation.navigateToOrderDetail
import com.example.store.feature.order_detail.navigation.orderDetailScreen
import com.example.store.feature.police_privacy.navigation.navigateToPolicePrivacy
import com.example.store.feature.police_privacy.navigation.policePrivacyScreen
import com.example.store.feature.product_detail.navigation.navigateToProductDetail
import com.example.store.feature.product_detail.navigation.productDetailScreen
import com.example.store.feature.product_listing.navigation.navigateToProductListing
import com.example.store.feature.product_listing.navigation.productListingScreen
import com.example.store.feature.profile.navigation.profileScreen
import com.example.store.feature.edit_profile.navigation.navigateToEditProfile
import com.example.store.feature.edit_profile.navigation.editProfileScreen
import com.example.store.feature.reviews.navigation.navigateToReviews
import com.example.store.feature.reviews.navigation.reviewsScreen
import com.example.store.feature.search.navigation.navigateToSearch
import com.example.store.feature.search.navigation.searchScreen
import com.example.store.feature.change_password.navigation.navigateToChangePassword
import com.example.store.feature.change_password.navigation.changePasswordScreen
import com.example.store.feature.shop.navigation.navigateToShop
import com.example.store.feature.shop.navigation.shopScreen
import com.example.store.feature.store.navigation.navigateToStore
import com.example.store.feature.store.navigation.storeScreen


@Composable
fun App(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
) {

    val cartItemsCount by viewModel.cartCount.collectAsStateWithLifecycle()
    val isOffline by viewModel.isOffline.collectAsStateWithLifecycle()

    if (!isOffline) {
        NoInternetConnection(
            onTryAgain = {
                //viewModel.tryAgain()
            }
        )
    } else {
        when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppContent(
                        modifier = modifier.weight(1f),
                        navController = navController,
                    )
                    BottomNavigationBar(
                        navController = navController,
                        cartItemsCount = cartItemsCount
                    )
                }
            }

            else -> {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    StoreNavigationRail(
                        navController = navController,
                        cartItemsCount = cartItemsCount
                    )
                    AppContent(
                        modifier = modifier.weight(1f),
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute
    ) {
        authentication(navController)

        homeScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onSeeAll = { navController.navigateToProductListing(it) },
            onSearch = { navController.navigateToSearch() }
        )

        cartScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onCheckout = { navController.navigateToCheckout() }
        )

        profileScreen(
            onMyOrdersClick = { navController.navigateToMyOrders() },
            onEditProfileClick = { navController.navigateToEditProfile() },
            onChangePasswordClick = { navController.navigateToChangePassword() },
            onHelpCenterClick = { navController.navigateToHelpCenter() },
            onPolicePrivacyClick = { navController.navigateToPolicePrivacy() },
            onAddressesClick = { navController.navigateToAddresses() }
        )

        helpCenterScreen(
            onNavigationUp = navController::navigateUp
        )

        shopScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onNavigateUp = navController::navigateUp
        )

        discoverScreen(
            onSearch = { navController.navigateToSearch() },
            onSelectCategory = { category, subcategory ->
                navController.navigateToShop(category, subcategory)
            }
        )

        editProfileScreen(
            onNavigateUp = navController::navigateUp
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
            onSuggestedProductsClick = { navController.navigateToProductDetail(it) },
            onStoreClick = { navController.navigateToStore(it) }
        )

        myOrdersScreen(
            onDetailClick = { navController.navigateToOrderDetail(0) },
            navController::navigateUp
        )

        storeScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onNavigateUp = navController::navigateUp
        )

        orderDetailScreen(navController::navigateUp)

        changePasswordScreen(navController::navigateUp)

        checkoutScreen(
            onNavigateUp = navController::navigateUp,
            onAddAddress = { navController.navigateToNewAddressScreen() }
        )

        newAddressScreen(onNavigateUp = navController::navigateUp)

        reviewsScreen(onNavigateUp = navController::navigateUp)
    }
}

@Composable
fun NoInternetConnection(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.ic_no_internet),
            contentDescription = null,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Nenhuma conexão de internet foi encontrada. Verifique sua conexão e tente novamente",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onTryAgain
        ) {
            Text(text = "Tentar novamente")
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        //App()
    }
}




