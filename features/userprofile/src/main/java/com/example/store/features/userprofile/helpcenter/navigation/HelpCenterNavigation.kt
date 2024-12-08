package com.example.store.features.userprofile.helpcenter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.helpcenter.HelpCenterScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object HelpCenterRoute

internal fun NavController.navigateToHelpCenter() = navigate(HelpCenterRoute)

internal fun NavGraphBuilder.helpCenterScreen(
    onNavigationUp: () -> Unit
){
    composable<HelpCenterRoute> {
        HelpCenterScreen(
            onNavigationUp = onNavigationUp
        )
    }
}