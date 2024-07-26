package com.example.store.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.store.feature.cart.navigation.CartRoute
import com.example.store.presentation.screens.checkout.navigation.FavoriteRoute
import com.example.store.feature.home.navigation.HomeRoute
import com.example.store.presentation.screens.profile.navigation.ProfileRoute
import com.example.store.presentation.screens.shop.navigation.ShopRoute


enum class TopLevelDestination (
    val title: String,
    val route: Any,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", HomeRoute, Icons.Outlined.Home, Icons.Filled.Home),
    SHOP("Loja", ShopRoute,Icons.Outlined.ShoppingCart,Icons.Filled.ShoppingCart),
    CART("Carrinho", CartRoute, Icons.Outlined.ShoppingBag,Icons.Filled.ShoppingBag),
    Favorite("Favoritos", FavoriteRoute, Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
    PROFILE("Perfil", ProfileRoute,Icons.Outlined.PersonOutline, Icons.Filled.Person)
}
