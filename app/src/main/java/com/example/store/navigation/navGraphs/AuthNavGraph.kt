package com.example.store.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.presentation.screens.autentication.forgot.navigation.forgotPasswordScreen
import com.example.store.presentation.screens.autentication.forgot.navigation.navigateToForgotPassword
import com.example.store.presentation.screens.autentication.login.navigation.LoginRoute
import com.example.store.presentation.screens.autentication.login.navigation.loginScreen
import com.example.store.presentation.screens.autentication.signup.navigation.navigateToSignUp
import com.example.store.presentation.screens.autentication.signup.navigation.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
data object AuthenticationRoute

fun NavGraphBuilder.authentication(
    navController: NavController
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