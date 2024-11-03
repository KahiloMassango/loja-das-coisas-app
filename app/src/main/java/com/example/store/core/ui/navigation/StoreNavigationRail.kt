package com.example.store.core.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.store.feature.cart.navigation.CartRoute
import com.example.store.navigation.TopLevelDestination

private val navBarRoutes = TopLevelDestination.entries.map { it.route::class }

private fun NavController.navigateToTopLevelDestination(route: Any) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun StoreNavigationRail(
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
    } ?: true

    AnimatedVisibility(
        visible = showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        Box(
            modifier = Modifier
                .shadow(24.dp, shape = RoundedCornerShape(bottomEnd = 12.dp, topEnd = 12.dp))
        ) {
            NavigationRail(
                modifier = modifier,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                TopLevelDestination.entries.forEach { destination ->
                    val b = currentRoute?.hierarchy?.any { it.route == destination.route } == true
                    val isSelected = currentRoute?.hasRoute(destination.route::class) == true
                    val isCart = currentBackStack?.toRoute<CartRoute>() == destination.route
                    NavigationRailItem(
                        modifier = Modifier.padding(bottom = 8.dp),
                        selected = isSelected,
                        onClick = {
                            navController.navigateToTopLevelDestination(destination.route)
                        },
                        label = {
                            Text(
                                text = destination.title,
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        icon = {
                            if (isCart && cartItemsCount > 0) {
                                CartIconWithBadge(
                                    cartItemsCount = cartItemsCount,
                                    icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                )
                            } else {
                                Icon(
                                    imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = null,
                                )
                            }
                        },
                        colors = NavigationRailItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
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