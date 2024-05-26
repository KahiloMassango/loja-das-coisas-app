package com.example.store.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.store.R
import kotlinx.serialization.Serializable


sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object Profile : Screen("Perfil")
    data object Shop : Screen("Loja")
    data object Cart : Screen("Carrinho")
    data object Details : Screen("Detalhes")

    data object AuthGraph : Screen("AuthGraph")
    data object Login : Screen("Entar")
    data object SignUp : Screen("Registar")
    data object ForgotPassword : Screen("RecuperarSenha")
}


data class TopLevelDestination (
    val route: String,
    @DrawableRes
    val unselectedIcon: Int,
    @DrawableRes
    val selectedIcon: Int
)


val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = Screen.Home.route,
        unselectedIcon = R.drawable.home,
        selectedIcon = R.drawable.selected_home
    ),
    TopLevelDestination(
        route = Screen.Shop.route,
        unselectedIcon = R.drawable.shop,
        selectedIcon = R.drawable.selected_shop
    ),
    TopLevelDestination(
        route = Screen.Cart.route,
        unselectedIcon = R.drawable.cart,
        selectedIcon = R.drawable.selected_cart
    ),
    TopLevelDestination(
        route = Screen.Profile.route,
        unselectedIcon = R.drawable.profile,
        selectedIcon = R.drawable.selected_profile
    ),
)
