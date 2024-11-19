package com.example.store.feature.edit_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.edit_profile.EditProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object EditProfileRoute


fun NavController.navigateToEditProfile() = navigate(EditProfileRoute)

fun NavGraphBuilder.editProfileScreen(
    onNavigateUp: () -> Unit
) {
    composable<EditProfileRoute>() {
        EditProfileScreen(
            onNavigateUp = onNavigateUp
        )

    }
}