package com.example.store.feature.autentication.forgot.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.autentication.forgot.ForgotPasswordScreen
import kotlinx.serialization.Serializable



@Serializable
data object ForgotPasswordRoute

fun NavController.navigateToForgotPassword() = navigate(ForgotPasswordRoute)

fun NavGraphBuilder.forgotPasswordScreen(onNavigateUp: () -> Unit,) {
    composable<ForgotPasswordRoute> {
        ForgotPasswordScreen(onNavigateUp = onNavigateUp)
    }
}