package com.example.store.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute


fun NavController.navigateToProfile() = navigate(ProfileRoute)

fun NavGraphBuilder.profileScreen(
    onNavigateUp: () -> Unit
) {
    composable<ProfileRoute>() {
        ProfileScreen(
            onNavigateUp = onNavigateUp
        )

    }
}