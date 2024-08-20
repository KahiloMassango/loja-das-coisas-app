package com.example.store.feature.police_privacy.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.police_privacy.PolicePrivacyScreen
import kotlinx.serialization.Serializable


@Serializable
data object PolicePrivacyRoute

fun NavController.navigateToPolicePrivacy() = navigate(PolicePrivacyRoute)

fun NavGraphBuilder.policePrivacyScreen(
    onNavigationUp: () -> Unit
) {
    composable<PolicePrivacyRoute> {
        PolicePrivacyScreen(
            onNavigationUp = onNavigationUp
        )
    }

}