package com.example.store.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.category.DiscoverScreen
import kotlinx.serialization.Serializable

@Serializable
object DiscoverRoute

fun NavController.navigateToDiscover() = navigate(DiscoverRoute)

fun NavGraphBuilder.discoverScreen(
    onSelectCategory: (section: String, category: String) -> Unit,
    onSearch: () -> Unit
) {
    composable<DiscoverRoute> {
        DiscoverScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )
    }
}