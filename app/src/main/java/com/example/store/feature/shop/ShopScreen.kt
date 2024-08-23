package com.example.store.feature.shop

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.shop.component.CategorySelectionScreen
import com.example.store.feature.shop.component.ShopListingScreen
import com.example.store.feature.shop.component.SortOption
import com.example.store.feature.shop.model.ShopScreenContent
import com.example.store.feature.shop.model.ShopSection
import com.example.store.feature.shop.model.kidsFilters
import com.example.store.feature.shop.model.menFilters
import com.example.store.feature.shop.model.womenFilters


@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onSearch: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    var currentContent by rememberSaveable { mutableStateOf(ShopScreenContent.Categories) }
    var currentSection by rememberSaveable { mutableStateOf(ShopSection.Women) }
    var currentCategory by rememberSaveable { mutableStateOf("") }
    var orderBy by rememberSaveable { mutableStateOf(SortOption.Popular.title) }
    val filters = when(currentSection){
        ShopSection.Men -> menFilters
        ShopSection.Women -> womenFilters
        ShopSection.Kids -> kidsFilters
    }
    var filter by rememberSaveable { mutableStateOf(filters[0].name) }


    AnimatedContent(
        modifier = modifier,
        targetState = currentContent,
        label = "AnimatedContent"
    ) { content ->
        when(content) {
            ShopScreenContent.Categories -> CategorySelectionScreen(
                currentSection = currentSection,
                onSearch = onSearch,
                onSectionClick = { section -> currentSection = section },
                onCategoryClick = { category ->
                    currentCategory = category
                    viewModel.getProducts(currentSection.name, currentCategory, filter, orderBy)
                    currentContent = ShopScreenContent.Products
                }
            )

            ShopScreenContent.Products -> when(uiState) {
                is ShopUiState.Loading -> LoadingScreen()
                is ShopUiState.Error -> ErrorScreen(onTryAgain = {
                    viewModel.getProducts(currentSection.name, currentCategory, filter, orderBy) }
                )
                is ShopUiState.Success -> ShopListingScreen(
                    section = currentSection,
                    category = currentCategory,
                    currentFilter = filter,
                    orderBy = orderBy,
                    products = uiState.products,
                    filters = filters,
                    onFilterChange = { filter = it },
                    onProductClick = { onProductClick(it) },
                    onOrderBy = { orderBy = it },
                    onSearch = onSearch,
                    onNavigateUp = {
                        currentContent = ShopScreenContent.Categories
                    }
                )
            }

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