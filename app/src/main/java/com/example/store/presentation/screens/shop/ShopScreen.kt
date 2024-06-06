package com.example.store.presentation.screens.shop

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.navigation.Screen
import com.example.store.presentation.screens.shop.components.CategorySection
import com.example.store.presentation.screens.shop.components.ProductListingContent
import com.example.store.presentation.screens.shop.components.CategorySelectionScreen
import com.example.store.presentation.screens.shop.components.ShopSection
import com.example.store.presentation.screens.shop.components.getCategorySectionFilters
import com.example.store.presentation.screens.shop.components.getSectionTitle
import com.example.store.ui.theme.StoreTheme

@Composable
fun ShopScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    // State to keep track of the current content being displayed on the ShopScreen
    var currentShopContent by rememberSaveable { mutableStateOf(ShopSection.Categories) }

    // State to save the current category section when navigating back from ShopCategories
    var currentCategorySection by rememberSaveable { mutableStateOf(CategorySection.Women) }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    AnimatedContent(
        modifier = modifier,
        targetState = currentShopContent,
        label = "AnimatedContent"
    ) { section ->
        when(section) {
            ShopSection.Products -> ProductListingContent(
                title = currentCategorySection.getSectionTitle(),
                category = selectedCategory,
                filterList= getCategorySectionFilters(currentCategorySection),
                onProductClick = {
                    navController.navigate(Screen.ProductDetail)
                },
                onNavigateUp = { currentShopContent = ShopSection.Categories }
            )
            ShopSection.Categories -> CategorySelectionScreen(
                currentSection = currentCategorySection,
                onSectionClick = { currentCategorySection = it },
                onCategoryClick = { category ->
                    selectedCategory = category
                    currentShopContent = ShopSection.Products
                    Log.d("ShopScreen", "${currentCategorySection.name} -> $category")
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