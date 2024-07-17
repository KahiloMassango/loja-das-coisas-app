package com.example.store.presentation.screens.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.home.HomeScreen
import com.example.store.presentation.screens.shop.navigation.ShopRoute
import kotlinx.serialization.Serializable


@Serializable
data object HomeRoute

fun NavController.navigateToHome() = navigate(HomeRoute)

fun NavGraphBuilder.homeScreen(onProductDetail: (Int) -> Unit) {
    composable<HomeRoute> {
        HomeScreen(onProductDetail = { onProductDetail(it) } )
    }
}