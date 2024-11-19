package com.example.store.feature.change_password.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.change_password.ChangePasswordScreen
import kotlinx.serialization.Serializable

@Serializable
data object ChangePasswordRoute

fun NavController.navigateToChangePassword() = navigate(ChangePasswordRoute)

fun NavGraphBuilder.changePasswordScreen(onNavigateUp: () -> Unit, ) {
    composable<ChangePasswordRoute> {
        ChangePasswordScreen(onNavigateUp = onNavigateUp)
    }
}