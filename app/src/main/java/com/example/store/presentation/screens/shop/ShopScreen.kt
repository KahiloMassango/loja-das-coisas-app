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
import com.example.store.presentation.screens.shop.components.ProductListingContent
import com.example.store.presentation.screens.shop.components.ShopCategories
import com.example.store.presentation.screens.shop.components.ShopSection
import com.example.store.presentation.screens.shop.components.filterList
import com.example.store.ui.theme.StoreTheme

@Composable
fun ShopScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var currentScreen by rememberSaveable { mutableStateOf(ShopSection.Categories) }
    var currentSection by rememberSaveable { mutableStateOf(ShopSection.Women) }
    var currentCategory by rememberSaveable { mutableStateOf("") }
    AnimatedContent(
        modifier = modifier,
        targetState = currentScreen,
        label = ""
    ) { state ->
        when(state) {
            ShopSection.Women -> ProductListingContent(
                title = "Mulheres",
                category = currentCategory,
                filterList= filterList,
                onProductClick = {
                    navController.navigate(Screen.ProductDetail)
                },
                onNavigateUp = { currentScreen = ShopSection.Categories }
            )
            ShopSection.Men-> ProductListingContent(
                title = "Homens",
                category = currentCategory,
                filterList= filterList,
                onProductClick = {
                    navController.navigate(Screen.ProductDetail)
                },
                onNavigateUp = { currentScreen = ShopSection.Categories }
            )
            ShopSection.Kids -> ProductListingContent(
                title = "Crianças",
                category = currentCategory,
                filterList= filterList,
                onProductClick = {
                    navController.navigate(Screen.ProductDetail)
                },
                onNavigateUp = { currentScreen = ShopSection.Categories }
            )
            ShopSection.Categories -> ShopCategories(
                currentSection = currentSection,
                onSectionClick = { currentSection = it },
                onCategoryClick = { category ->
                    currentScreen = currentSection
                    currentCategory = category
                    Log.d("info","${currentSection.name} -> $category")
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