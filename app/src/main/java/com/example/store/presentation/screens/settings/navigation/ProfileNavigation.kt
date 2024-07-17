package com.example.store.presentation.screens.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute

fun NavController.navigateToSettings() = navigate(SettingsRoute)

fun NavGraphBuilder.settingsScreen(onNavigateUp: () -> Unit, ) {
    composable<SettingsRoute> {
        SettingsScreen(onNavigateUp = onNavigateUp)
    }
}