package com.example.store.features.discover.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.discover.CategoryScreen
import kotlinx.serialization.Serializable

@Serializable
object CategoryRoute

fun NavGraphBuilder.categoryScreen(
    onSelectCategory: (category: String, subcategory: String) -> Unit,
    onSearch: () -> Unit
) {
    composable<CategoryRoute> {
        CategoryScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )
    }
}