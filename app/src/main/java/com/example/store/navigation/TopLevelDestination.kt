package com.example.store.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.store.features.cart.navigation.CartRoute
import com.example.store.features.discover.navigation.DiscoverRoute
import com.example.store.features.home.navigation.HomeRoute
import com.example.store.features.userprofile.navigation.UserProfileRoute


enum class TopLevelDestination (
    val title: String,
    val route: Any,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
){
    HOME("Home", HomeRoute, Icons.Outlined.Home, Icons.Filled.Home),
    DISCOVER("Descobrir", DiscoverRoute,Icons.Outlined.Category,Icons.Filled.Category),
    CART("Cesto", CartRoute, Icons.Outlined.ShoppingBasket,Icons.Filled.ShoppingBasket),
    PROFILE("Perfil", UserProfileRoute, Icons.Outlined.Person, Icons.Filled.Person)
}

