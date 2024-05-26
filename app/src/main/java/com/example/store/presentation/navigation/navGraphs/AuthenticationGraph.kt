package com.example.store.presentation.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.presentation.navigation.Screen
import com.example.store.presentation.screens.autentication.forgot.ForgotPasswordScreen
import com.example.store.presentation.screens.autentication.login.LoginScreen
import com.example.store.presentation.screens.autentication.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(
        route = Screen.AuthGraph.route,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onSignUp = { navController.navigate(Screen.SignUp.route) },
                onForgotPassword = { navController.navigate(Screen.ForgotPassword.route) },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onLogin = { navController.popBackStack(route = Screen.Login.route, inclusive = false) },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }
}