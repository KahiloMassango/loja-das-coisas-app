package com.example.store.features.userprofile.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit
) {
    ProfileContent(
        modifier = modifier,
        onMyOrdersClick = onMyOrdersClick,
        onEditProfileClick = onEditProfileClick,
        onChangePasswordClick = onChangePasswordClick,
        onHelpCenterClick = onHelpCenterClick,
        onAddressesClick = onAddressesClick,
        onPolicePrivacyClick = onPolicePrivacyClick,
        userPhotoUrl = null,
        onLogoutClick = {}
    )
}




@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProfileScreen(
            onMyOrdersClick = {},
            onEditProfileClick = {},
            onChangePasswordClick = {},
            onHelpCenterClick = {},
            onAddressesClick = {},
            onPolicePrivacyClick = {}
        )
    }
}
