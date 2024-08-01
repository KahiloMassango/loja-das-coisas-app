package com.example.store.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

fun NavController.navigateToSearch() = navigate(SearchRoute)

fun NavGraphBuilder.searchScreen(
    onNavigateUp: () -> Unit,
    onProductClick: (String) -> Unit
) {
    composable<SearchRoute> {
        SearchScreen(
            onNavigateUp = onNavigateUp,
            onProductClick = { onProductClick(it) }
        )
    }
}