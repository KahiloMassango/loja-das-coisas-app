package com.example.store.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.navigation.Route
import com.example.store.presentation.screens.autentication.forgot.ForgotPasswordScreen
import com.example.store.presentation.screens.autentication.login.LoginScreen
import com.example.store.presentation.screens.autentication.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
     navigation<Route.AuthGraph>(
         startDestination = Route.Login,
    ) {
         composable<Route.Login> {
             LoginScreen(navController)
         }
         composable<Route.SignUp> {
             SignUpScreen(navController)
         }
         composable<Route.ForgotPassword> {
             ForgotPasswordScreen(navController)

         }
    }
}