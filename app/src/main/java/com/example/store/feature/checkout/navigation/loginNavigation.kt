package com.example.store.feature.checkout.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.favorite.FavoriteScreen
import kotlinx.serialization.Serializable


@Serializable
data object FavoriteRoute

fun NavController.navigateToFavorite() = navigate(FavoriteRoute)

fun NavGraphBuilder.favoriteScreen(onProductDetail: (String) -> Unit) {
    composable<FavoriteRoute> {
        FavoriteScreen(onProductDetail = { onProductDetail(it) })
    }
}