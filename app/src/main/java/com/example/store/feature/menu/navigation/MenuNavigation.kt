package com.example.store.feature.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.menu.MenuScreen
import kotlinx.serialization.Serializable


@Serializable
data object MenuRoute

fun NavGraphBuilder.menuScreen(
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit
) {
    composable<MenuRoute> {
        MenuScreen(
            onMyOrdersClick = onMyOrdersClick,
            onEditProfileClick = onEditProfileClick,
            onChangePasswordClick = onChangePasswordClick,
            onHelpCenterClick = onHelpCenterClick,
            onPolicePrivacyClick = onPolicePrivacyClick,
            onAddressesClick = onAddressesClick
        )
    }
}