package com.example.store.feature.autentication.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.autentication.login.LoginScreen
import kotlinx.serialization.Serializable


@Serializable
data object LoginRoute


fun NavController.navigateToLogin() = navigate(LoginRoute)

fun NavGraphBuilder.loginScreen(
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<LoginRoute> {
        LoginScreen(
            onSignUp = onSignUp,
            onForgotPassword = onForgotPassword,
            onNavigateUp = onNavigateUp,
        )
    }
}