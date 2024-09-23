package com.example.store.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.store.feature.cart.navigation.CartRoute
import com.example.store.feature.category.navigation.CategoryRoute
import com.example.store.feature.favorite.navigation.FavoriteRoute
import com.example.store.feature.home.navigation.HomeRoute
import com.example.store.feature.profile.navigation.ProfileRoute


enum class TopLevelDestination (
    val title: String,
    val route: Any,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", HomeRoute, Icons.Outlined.Home, Icons.Filled.Home),
    SHOP("Loja", CategoryRoute,Icons.Outlined.Storefront,Icons.Filled.Storefront),
    CART("Cesto", CartRoute, Icons.Outlined.ShoppingBasket,Icons.Filled.ShoppingBasket),
    FAVORITE("Favorito", FavoriteRoute, Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
    PROFILE("Perfil", ProfileRoute,Icons.Outlined.PersonOutline, Icons.Filled.Person)
}
