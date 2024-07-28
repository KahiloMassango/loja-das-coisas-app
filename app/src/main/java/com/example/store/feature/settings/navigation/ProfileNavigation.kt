package com.example.store.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute

fun NavController.navigateToSettings() = navigate(SettingsRoute)

fun NavGraphBuilder.settingsScreen(onNavigateUp: () -> Unit, ) {
    composable<SettingsRoute> {
        SettingsScreen(onNavigateUp = onNavigateUp)
    }
}