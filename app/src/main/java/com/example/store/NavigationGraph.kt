package com.example.store

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.common.BottomNavigationBar
import com.example.store.presentation.navigation.Screen
import com.example.store.presentation.navigation.navGraphs.authNavGraph
import com.example.store.presentation.screens.cart.CartScreen
import com.example.store.presentation.screens.home.HomeScreen
import com.example.store.presentation.screens.profile.ProfileScreen
import com.example.store.presentation.screens.shop.ShopScreen


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screen.Home.route
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Cart.route,
        Screen.Profile.route,
        Screen.Shop.route,
    )
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            authNavGraph(navController)
            composable(Screen.Home.route) {
                HomeScreen(
                    onAuthGraph = { navController.navigate(Screen.SignUp.route)  }
                )
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Shop.route) {
                ShopScreen()
            }
        }
        AnimatedVisibility(
            visible = showBottomBar,
        ) {
            BottomNavigationBar(navController = navController)
        }
    }
}




