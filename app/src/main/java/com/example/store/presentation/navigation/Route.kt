package com.example.store.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen{

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

data class TopLevelDestination (
    val title: String,
    val route: Screen,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)


val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        title = "Home",
        route = Screen.Home,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    TopLevelDestination(
        title = "Loja",
        route = Screen.Shop,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart
    ),
    TopLevelDestination(
        title = "Carrinho",
        route = Screen.Cart,
        unselectedIcon = Icons.Outlined.ShoppingBag,
        selectedIcon = Icons.Filled.ShoppingBag
    ),
    TopLevelDestination(
        title = "Perfil",
        route = Screen.Profile,
        unselectedIcon = Icons.Outlined.PersonOutline,
        selectedIcon = Icons.Filled.Person
    )
)
