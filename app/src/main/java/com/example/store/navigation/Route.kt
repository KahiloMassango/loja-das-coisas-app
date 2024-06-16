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
sealed interface Screen {

    @Serializable
    data object TopLevelGraph: Screen
    @Serializable
    data object Home: Screen
    @Serializable
    data object Profile: Screen
    @Serializable
    data object Cart: Screen
    @Serializable
    data object Shop: Screen
    @Serializable
    data object Settings: Screen
    data object MyReviews: Screen
    @Serializable
    data object ProductDetail: Screen
    @Serializable
    data object AuthGraph: Screen
    @Serializable
    data object Login: Screen
    @Serializable
    data object SignUp: Screen
    @Serializable
    data object ForgotPassword: Screen


}

enum class AppDestinations (
    val title: String,
    val route: Screen,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", Screen.Home, Icons.Outlined.Home, Icons.Filled.Home),
    SHOP("Loja",Screen.Shop,Icons.Outlined.ShoppingCart,Icons.Filled.ShoppingCart),
    CART("Carrinho", Screen.Cart, Icons.Outlined.ShoppingBag,Icons.Filled.ShoppingBag),
    PROFILE("Perfil",Screen.Profile,Icons.Outlined.PersonOutline, Icons.Filled.Person)
}
