package com.example.store.core.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import com.example.store.navigation.TopLevelDestination

fun NavController.navigateToTopLevelDestination(destination: TopLevelDestination) {
    navigate(destination.route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}