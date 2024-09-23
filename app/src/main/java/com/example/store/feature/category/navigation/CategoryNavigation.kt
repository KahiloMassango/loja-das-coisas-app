package com.example.store.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.category.CategoryScreen
import kotlinx.serialization.Serializable

@Serializable
object CategoryRoute

fun NavController.navigateToCategory() = navigate(CategoryRoute)

fun NavGraphBuilder.categoryScreen(
    onSelectCategory: (section: String, category: String) -> Unit,
    onSearch: () -> Unit = {}
) {
    composable<CategoryRoute> {
        CategoryScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )
    }
}