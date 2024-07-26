package com.example.store.feature.autentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.feature.autentication.forgot.navigation.forgotPasswordScreen
import com.example.store.feature.autentication.forgot.navigation.navigateToForgotPassword
import com.example.store.feature.autentication.login.navigation.LoginRoute
import com.example.store.feature.autentication.login.navigation.loginScreen
import com.example.store.feature.autentication.signup.navigation.navigateToSignUp
import com.example.store.feature.autentication.signup.navigation.signUpScreen
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