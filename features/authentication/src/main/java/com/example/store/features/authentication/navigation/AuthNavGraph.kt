package com.example.store.features.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.features.authentication.forgot.navigation.forgotPasswordScreen
import com.example.store.features.authentication.forgot.navigation.navigateToForgotPassword
import com.example.store.features.authentication.login.navigation.LoginRoute
import com.example.store.features.authentication.login.navigation.loginScreen
import com.example.store.features.authentication.signup.navigation.navigateToSignUp
import com.example.store.features.authentication.signup.navigation.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
data object AuthenticationRoute

fun NavGraphBuilder.authenticationRoute(
    navController: NavController,
) {
    navigation<AuthenticationRoute>(
        startDestination = LoginRoute,
    ) {

        loginScreen(
            onSignUp = { navController.navigateToSignUp() },
            onForgotPassword = { navController.navigateToForgotPassword() },
            onNavigateUp = navController::navigateUp
        )

        signUpScreen(
            onNavigateLogin = navController::navigateUp,
            onNavigateUp = { navController.navigateUp() }
        )

        forgotPasswordScreen(onNavigateUp = navController::navigateUp)
    }
}