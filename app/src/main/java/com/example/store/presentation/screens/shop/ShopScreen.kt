package com.example.store.presentation.screens.shop

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.component.ThemePreviews
import com.example.store.navigation.Screen
import com.example.store.presentation.screens.shop.model.CategorySection
import com.example.store.presentation.screens.shop.component.CategorySelectionScreen
import com.example.store.presentation.screens.shop.component.ProductListingScreen
import com.example.store.presentation.screens.shop.model.ShopSection
import com.example.store.presentation.screens.shop.model.getCategorySectionFilters
import com.example.store.ui.theme.StoreTheme

data class ShopScreenUiState(
    val currentContent: ShopSection = ShopSection.Categories,
    val categorySection: CategorySection = CategorySection.Women,
    val selectedCategory: String = ""

)

@Composable
fun ShopScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var uiState by remember { mutableStateOf(ShopScreenUiState()) }

    AnimatedContent(
        modifier = modifier,
        targetState = uiState.currentContent,
        label = "AnimatedContent"
    ) { section ->
        when(section) {
            ShopSection.Products -> ProductListingScreen(
                productSection = uiState.categorySection,
                productCategory = uiState.selectedCategory,
                filterList= getCategorySectionFilters(uiState.categorySection),
                onFilterChange = { /*TODO: Implement filter change */ },
                onProductClick = {
                    navController.navigate(Screen.ProductDetail)
                },
                onNavigateUp = { uiState = uiState.copy(currentContent = ShopSection.Categories)  }
            )
            ShopSection.Categories -> CategorySelectionScreen(
                currentSection = uiState.categorySection,
                onSectionClick = { uiState = uiState.copy(categorySection = it) },
                onCategoryClick = { category ->
                    uiState = uiState.copy(selectedCategory = category)
                    uiState = uiState.copy(currentContent = ShopSection.Products)
                    Log.d("ShopScreen", "${uiState.categorySection.name} -> $category")
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
            rememberNavController()
        )
    }
}