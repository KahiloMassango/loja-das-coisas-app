package com.example.store.features.userprofile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.features.userprofile.changepassword.navigation.changePasswordScreen
import com.example.store.features.userprofile.deliveryaddress.navigation.deliveryAddressesScreen
import com.example.store.features.userprofile.editprofile.navigation.editProfileScreen
import com.example.store.features.userprofile.helpcenter.navigation.helpCenterScreen
import com.example.store.features.userprofile.orderdeail.navigation.orderDetailScreen
import com.example.store.features.userprofile.orders.navigation.myOrdersScreen
import com.example.store.features.userprofile.policyprivacy.navigation.policePrivacyScreen
import com.example.store.features.userprofile.profile.navigation.ProfileRoute
import com.example.store.features.userprofile.profile.navigation.profileScreen
import kotlinx.serialization.Serializable

@Serializable
data object UserProfileRoute

fun NavGraphBuilder.userProfile(
    onMyOrdersClick: () -> Unit,
    onOrderClick: (String) -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit,
    onAddNewAddress: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    navigation<UserProfileRoute>(startDestination = ProfileRoute) {
        profileScreen(
            onMyOrdersClick = onMyOrdersClick,
            onEditProfileClick = onEditProfileClick,
            onChangePasswordClick = onChangePasswordClick,
            onHelpCenterClick = onHelpCenterClick,
            onAddressesClick = onAddressesClick,
            onPolicePrivacyClick = onPolicePrivacyClick
        )
        policePrivacyScreen(
            onNavigationUp = onNavigateUp
        )
        editProfileScreen(
            onNavigateUp = onNavigateUp
        )

        helpCenterScreen(
            onNavigationUp = onNavigateUp
        )
        myOrdersScreen(
            onOrderClick = onOrderClick,
            onNavigateUp = onNavigateUp
        )
        orderDetailScreen(
            onNavigateUp = onNavigateUp
        )
        changePasswordScreen(
            onNavigateUp = onNavigateUp
        )
        deliveryAddressesScreen(
            onAddNewAddress = onAddNewAddress,
            onNavigateUp = onNavigateUp
        )
    }
}