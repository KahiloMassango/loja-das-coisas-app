package com.example.store.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.store.navigation.BottomNavigationBar
import com.example.store.navigation.Route
import com.example.store.navigation.navGraphs.authNavGraph
import com.example.store.navigation.navGraphs.topLevelGraph
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.checkout.CheckoutScreen
import com.example.store.presentation.screens.my_orders.MyOrdersScreen
import com.example.store.presentation.screens.product_detail.ProductDetailsScreen
import com.example.store.presentation.screens.settings.SettingsScreen
import com.example.store.ui.theme.StoreTheme


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = Route.TopLevelGraph
        ) {
            authNavGraph(navController)
            topLevelGraph(navController)

            composable<Route.ProductDetail> {
                ProductDetailsScreen(navController)
            }
            composable<Route.MyOrders> {
                MyOrdersScreen(navController = navController)
            }
            composable<Route.Settings> {
                SettingsScreen(navController = navController)
            }
            composable<Route.Checkout> {
                CheckoutScreen(navController = navController)
            }
        }
        BottomNavigationBar(navController)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        NavigationGraph()
    }
}




