package com.example.store.presentation.screens.shop

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.navigation.Route
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.shop.component.CategorySelectionScreen
import com.example.store.presentation.screens.shop.component.ProductListingScreen
import com.example.store.presentation.screens.shop.model.ShopScreenContent
import com.example.store.presentation.screens.shop.model.ShopScreenUiState
import com.example.store.ui.theme.StoreTheme


@Composable
fun ShopScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var uiState by remember { mutableStateOf(ShopScreenUiState()) }

    AnimatedContent(
        modifier = modifier,
        targetState = uiState.content,
        label = "AnimatedContent"
    ) { section ->
        when(section) {
            ShopScreenContent.Products -> ProductListingScreen(
                section = uiState.section,
                category = uiState.category,
                onFilterChange = { /*TODO: Implement filter change */ },
                onProductClick = {
                    navController.navigate(Route.ProductDetail)
                },
                onNavigateUp = { uiState = uiState.copy(content = ShopScreenContent.Categories)  }
            )
            ShopScreenContent.Categories -> CategorySelectionScreen(
                currentSection = uiState.section,
                onSectionClick = { uiState = uiState.copy(section = it) },
                onCategoryClick = { category ->
                    uiState = uiState.copy(category = category)
                    uiState = uiState.copy(content = ShopScreenContent.Products)
                    Log.d("ShopScreen", "${uiState.section.name} -> $category")
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