package com.example.store.features.discover

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.features.discover.category.navigation.CategoryRoute
import com.example.store.features.discover.category.navigation.categoryScreen
import com.example.store.features.discover.shop.navigation.ShopRoute
import com.example.store.features.discover.shop.navigation.shopScreen
import kotlinx.serialization.Serializable

@Serializable
data object DiscoverRoute

fun NavGraphBuilder.discoverScreen(
    onSelectCategory: (String, String) -> Unit,
    onProductClick: (String) -> Unit,
    onSearch: () -> Unit,
    onNavigateUp: () -> Unit
) {
    navigation<DiscoverRoute>(CategoryRoute) {

        categoryScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )

        shopScreen(
            onProductClick = { onProductClick(it) },
            onNavigateUp = onNavigateUp
        )

    }
}
