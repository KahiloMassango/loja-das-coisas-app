package com.example.store.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
data object HomeRoute

fun NavController.navigateToHome() = navigate(HomeRoute)

fun NavGraphBuilder.homeScreen(
    onProductClick: (String) -> Unit,
    onSeeAll: (String) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            onProductClick = { onProductClick(it) },
            onSeeAll = { onSeeAll(it) }
        )
    }
}