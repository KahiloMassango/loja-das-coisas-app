package com.example.store.presentation.screens.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.profile.ProfileScreen
import kotlinx.serialization.Serializable


@Serializable
data object ProfileRoute


fun NavController.navigateToProfile() = navigate(ProfileRoute)

fun NavGraphBuilder.profileScreen(
    onMyOrdersClick: () -> Unit,
    onMyReviewsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onMyOrdersClick = onMyOrdersClick,
            onMyReviewsClick = onMyReviewsClick,
            onSettingsClick = onSettingsClick
        )
    }
}