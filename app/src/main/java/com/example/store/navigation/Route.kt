package com.example.store.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object TopLevelGraph: Route
    @Serializable
    data object Home: Route
    @Serializable
    data object Profile: Route
    @Serializable
    data object Cart: Route
    @Serializable
    data object Shop: Route
    @Serializable
    data object Settings: Route
    @Serializable
    data object MyOrders: Route
    @Serializable
    data object ProductDetail: Route
    @Serializable
    data object AuthGraph: Route
    @Serializable
    data object Login: Route
    @Serializable
    data object SignUp: Route
    @Serializable
    data object ForgotPassword: Route


}

enum class AppDestinations (
    val title: String,
    val route: Route,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", Route.Home, Icons.Outlined.Home, Icons.Filled.Home),
    SHOP("Loja",Route.Shop,Icons.Outlined.ShoppingCart,Icons.Filled.ShoppingCart),
    CART("Carrinho", Route.Cart, Icons.Outlined.ShoppingBag,Icons.Filled.ShoppingBag),
    PROFILE("Perfil",Route.Profile,Icons.Outlined.PersonOutline, Icons.Filled.Person)
}
