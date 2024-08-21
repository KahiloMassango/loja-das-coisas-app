package com.example.store.feature.help_center.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.help_center.HelpCenterScreen
import kotlinx.serialization.Serializable

@Serializable
data object HelpCenterRoute

fun NavController.navigateToHelpCenter() = navigate(HelpCenterRoute)

fun NavGraphBuilder.helpCenterScreen(
    onNavigationUp: () -> Unit
){
    composable<HelpCenterRoute> {
        HelpCenterScreen(
            onNavigationUp = onNavigationUp
        )
    }
}