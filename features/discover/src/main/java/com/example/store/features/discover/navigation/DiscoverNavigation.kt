package com.example.store.features.discover.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.discover.DiscoverScreen
import kotlinx.serialization.Serializable

@Serializable
object DiscoverRoute


fun NavGraphBuilder.discoverScreen(
    onSelectCategory: (category: String, subcategory: String) -> Unit,
    onSearch: () -> Unit
) {
    composable<DiscoverRoute> {
        DiscoverScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )
    }
}