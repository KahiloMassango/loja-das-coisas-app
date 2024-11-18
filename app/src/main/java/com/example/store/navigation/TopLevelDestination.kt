package com.example.store.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.store.feature.cart.navigation.CartRoute
import com.example.store.feature.discover.navigation.DiscoverRoute
import com.example.store.feature.favorite.navigation.FavoriteRoute
import com.example.store.feature.home.navigation.HomeRoute
import com.example.store.feature.menu.navigation.MenuRoute


enum class TopLevelDestination (
    val title: String,
    val route: Any,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", HomeRoute, Icons.Outlined.Home, Icons.Filled.Home),
    DISCOVER("Descobrir", DiscoverRoute,Icons.Outlined.Category,Icons.Filled.Category),
    CART("Cesto", CartRoute, Icons.Outlined.ShoppingBasket,Icons.Filled.ShoppingBasket),
    FAVORITE("Favorito", FavoriteRoute, Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
    MENU("Menu", MenuRoute, Icons.Outlined.Menu, Icons.Outlined.Menu)
}

