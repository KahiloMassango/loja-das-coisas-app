package com.example.store.feature.store.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.store.StoreScreen
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class StoreRoute(val id: String)

fun NavController.navigateToStore(id: String) = navigate(StoreRoute(id))

fun NavGraphBuilder.storeScreen(
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<StoreRoute> {
        StoreScreen(
            onProductClick = onProductClick,
            onNavigateUp = onNavigateUp
        )
    }
}

