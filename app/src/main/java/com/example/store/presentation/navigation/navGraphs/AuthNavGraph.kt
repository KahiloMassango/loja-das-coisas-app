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
     navigation<Screen.AuthGraph>(
         startDestination = Screen.Login,
    ) {
         composable<Screen.Login> {
             LoginScreen(navController)
         }
         composable<Screen.SignUp> {
             SignUpScreen(navController)
         }
         composable<Screen.ForgotPassword> {
             ForgotPasswordScreen(navController)

         }
    }
}