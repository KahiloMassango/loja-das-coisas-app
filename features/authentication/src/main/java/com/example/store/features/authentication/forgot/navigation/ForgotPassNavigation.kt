package com.example.store.features.authentication.forgot.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.authentication.forgot.ForgotPasswordScreen
import kotlinx.serialization.Serializable


@Serializable
data object ForgotPasswordRoute

fun NavController.navigateToForgotPassword() = navigate(ForgotPasswordRoute)

fun NavGraphBuilder.forgotPasswordScreen(onNavigateUp: () -> Unit) {
    composable<ForgotPasswordRoute> {
        ForgotPasswordScreen(onNavigateUp = onNavigateUp)
    }
}