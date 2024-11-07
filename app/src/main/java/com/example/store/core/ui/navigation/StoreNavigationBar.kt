package com.example.store.core.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.store.feature.cart.navigation.CartRoute
import com.example.store.navigation.TopLevelDestination

private val navBarRoutes = TopLevelDestination.entries.map { it.route::class }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    cartItemsCount: Int,
) {

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination
    val showBottomBar = currentBackStack?.destination?.hierarchy?.any { backStack ->
        navBarRoutes.forEach {
            if (backStack.hasRoute(it)) {
                return@any true
            }
        }
        false
    } != false

    AnimatedVisibility(
        visible = showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ){
        Surface(
            modifier = Modifier
                .shadow(24.dp, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp) )
        ) {
            NavigationBar(
                modifier = modifier.clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                TopLevelDestination.entries.forEach { destination ->
                    val isSelected = currentRoute?.hasRoute(destination.route::class) ?: false
                    val isCart = currentBackStack?.toRoute<CartRoute>() == destination.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigateToTopLevelDestination(destination)
                        },
                        label = null/*{
                         Text(
                             text = destination.title,
                             style = MaterialTheme.typography.labelSmall,
                             fontSize = 10.sp,
                             fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                         )
                     }*/,
                        icon = {
                            if (isCart && cartItemsCount > 0) {
                                CartIconWithBadge(
                                    cartItemsCount = cartItemsCount,
                                    icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon,)
                            } else {
                                Icon(
                                    imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = null,
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.inverseOnSurface,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    )
                }
            }
        }
    }
}
