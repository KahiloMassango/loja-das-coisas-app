package com.example.store.presentation.screens.autentication.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.autentication.login.navigation.LoginRoute
import com.example.store.presentation.screens.autentication.signup.SignUpScreen
import kotlinx.serialization.Serializable


@Serializable
data object SignUpRoute


fun NavController.navigateToSignUp() = navigate(SignUpRoute)

fun NavGraphBuilder.signUpScreen(
    onNavigateLogin: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<SignUpRoute> {
        SignUpScreen(
            onNavigateLogin = onNavigateLogin,
            onNavigateUp = onNavigateUp,
        )
    }
}