package com.example.store.feature.shop

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.data.mock.productList
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.shop.component.CategorySelectionScreen
import com.example.store.feature.shop.component.ProductListingScreen
import com.example.store.feature.shop.model.ShopScreenContent
import com.example.store.feature.shop.model.ShopSection


@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onSearch: () -> Unit,
) {
    var currentContent by rememberSaveable { mutableStateOf(ShopScreenContent.Categories) }
    var currentSection by rememberSaveable { mutableStateOf(ShopSection.Women) }
    var currentCategory by rememberSaveable { mutableStateOf("") }


    AnimatedContent(
        modifier = modifier,
        targetState = currentContent,
        label = "AnimatedContent"
    ) { content ->
        when(content) {
            ShopScreenContent.Products -> ProductListingScreen(
                section = currentSection,
                category = currentCategory,
                products = productList,
                onSearch = onSearch,
                onFilterChange = { /*TODO: Implement filter change */ },
                onProductClick = { onProductClick(it) },
                onFavoriteClick = { /* TODO */ },
                onNavigateUp = {
                    currentContent = ShopScreenContent.Categories
                }
            )
            ShopScreenContent.Categories -> CategorySelectionScreen(
                currentSection = currentSection,
                onSearch = onSearch,
                onSectionClick = { section -> currentSection = section },
                onCategoryClick = { category ->
                    currentCategory = category
                    currentContent = ShopScreenContent.Products
                    Log.d("ShopScreen", "${currentSection.name} -> $category")
                }
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ShopScreen(
            onProductClick = {},
        ) {}
    }
}