package com.example.store.feature.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.menu.MenuScreen
import kotlinx.serialization.Serializable


@Serializable
data object MenuRoute

fun NavGraphBuilder.menuScreen(
    onMyOrdersClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit
) {
    composable<MenuRoute> {
        MenuScreen(
            onMyOrdersClick = onMyOrdersClick,
            onProfileClick = onProfileClick,
            onSettingsClick = onSettingsClick,
            onHelpCenterClick = onHelpCenterClick,
            onPolicePrivacyClick = onPolicePrivacyClick,
            onAddressesClick = onAddressesClick
        )
    }
}