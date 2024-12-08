package com.example.store.features.userprofile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.features.userprofile.changepassword.navigation.changePasswordScreen
import com.example.store.features.userprofile.changepassword.navigation.navigateToChangePassword
import com.example.store.features.userprofile.deliveryaddress.navigation.deliveryAddressesScreen
import com.example.store.features.userprofile.deliveryaddress.navigation.navigateToAddresses
import com.example.store.features.userprofile.editprofile.navigation.editProfileScreen
import com.example.store.features.userprofile.editprofile.navigation.navigateToEditProfile
import com.example.store.features.userprofile.helpcenter.navigation.helpCenterScreen
import com.example.store.features.userprofile.helpcenter.navigation.navigateToHelpCenter
import com.example.store.features.userprofile.orderdeail.navigation.navigateToOrderDetail
import com.example.store.features.userprofile.orderdeail.navigation.orderDetailScreen
import com.example.store.features.userprofile.orders.navigation.myOrdersScreen
import com.example.store.features.userprofile.orders.navigation.navigateToMyOrders
import com.example.store.features.userprofile.policyprivacy.navigation.navigateToPolicePrivacy
import com.example.store.features.userprofile.policyprivacy.navigation.policePrivacyScreen
import com.example.store.features.userprofile.profile.navigation.ProfileRoute
import com.example.store.features.userprofile.profile.navigation.profileScreen
import kotlinx.serialization.Serializable

@Serializable
data object UserProfileRoute

fun NavController.navigateToUserProfile() = navigate(UserProfileRoute)

fun NavGraphBuilder.userProfileScreen(
    navController: NavController,
    onAddNewAddress: () -> Unit
) {
    navigation<UserProfileRoute>(startDestination = ProfileRoute) {
        profileScreen(
            onMyOrdersClick = { navController.navigateToMyOrders() },
            onEditProfileClick = { navController.navigateToEditProfile() },
            onChangePasswordClick = { navController.navigateToChangePassword() },
            onHelpCenterClick = { navController.navigateToHelpCenter() },
            onAddressesClick = { navController.navigateToAddresses() },
            onPolicePrivacyClick = { navController.navigateToPolicePrivacy() }
        )
        policePrivacyScreen(
            onNavigationUp = navController::navigateUp
        )
        editProfileScreen(
            onNavigateUp = navController::navigateUp
        )

        helpCenterScreen(
            onNavigationUp = navController::navigateUp
        )
        myOrdersScreen(
            onDetailClick = { navController.navigateToOrderDetail(it) },
            onNavigateUp = navController::navigateUp
        )
        orderDetailScreen(
            onNavigateUp = navController::navigateUp
        )
        changePasswordScreen(
            onNavigateUp = navController::navigateUp
        )
        deliveryAddressesScreen(
            onAddNewAddress = onAddNewAddress,
            onNavigateUp = navController::navigateUp
        )
    }
}