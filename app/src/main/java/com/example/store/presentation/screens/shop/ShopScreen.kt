package com.example.store.presentation.screens.shop

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.common.StoreCenteredTopBar
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.screens.shop.components.AdvertisementCard
import com.example.store.presentation.screens.shop.components.CategoriesList
import com.example.store.presentation.screens.shop.components.Filter
import com.example.store.presentation.screens.shop.components.FilterContainer
import com.example.store.presentation.screens.shop.components.ProductListingContent
import com.example.store.presentation.screens.shop.components.ShopSection
import com.example.store.presentation.screens.shop.components.ShopSectionTabs
import com.example.store.presentation.screens.shop.components.filterList
import com.example.store.presentation.screens.shop.components.kidsCategories
import com.example.store.presentation.screens.shop.components.menCategories
import com.example.store.presentation.screens.shop.components.womenCategories
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
            ShopSection.Women -> ShopContent(
                title = "Mulheres",
                category = currentCategory,
                filterList= filterList,
                onNavigateUp = { currentScreen = ShopSection.Categories }
            )
            ShopSection.Men-> ShopContent(
                title = "Homens",
                category = currentCategory,
                filterList= filterList,
                onNavigateUp = { currentScreen = ShopSection.Categories }
            )
            ShopSection.Kids -> ShopContent(
                title = "Crianças",
                category = currentCategory,
                filterList= filterList,
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

@Composable
private fun ShopCategories(
    currentSection: ShopSection,
    onSectionClick: (ShopSection) -> Unit,
    onCategoryClick: (category: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = "Categoria",
                canNavigateBack = false
            )
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                ShopSectionTabs(
                    selectedSection = currentSection,
                    onTabClick = { onSectionClick(it) }
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    AdvertisementCard(
                        modifier = Modifier,
                        mainText = "summer sales",
                        description = "up to 50% off",
                        onClick = { /* TODO */ }
                    )
                    AnimatedContent(
                        modifier = Modifier.weight(1f),
                        targetState = currentSection,
                        label = ""
                    ) { section ->
                        when (section) {
                            ShopSection.Women -> CategoriesList(
                                categories = womenCategories,
                                onCategoryClick = { category ->
                                    onCategoryClick(category)
                                }
                            )
                            ShopSection.Men -> CategoriesList(
                                categories = menCategories,
                                onCategoryClick = { category ->
                                    onCategoryClick(category)
                                }
                            )
                            ShopSection.Kids -> CategoriesList(
                                categories = kidsCategories,
                                onCategoryClick = { category ->
                                    onCategoryClick(category)
                                }
                            )
                            else -> CategoriesList(
                                categories = womenCategories,
                                onCategoryClick = { category ->
                                    onCategoryClick(category)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShopContent(
    title: String,
    category: String,
    filterList: List<Filter>,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onNavigateUp() }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = title,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                if (category != "Shoes") {
                    FilterContainer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        filters = filterList,
                        onFilterSelected = { /* TODO */ }
                    )
                }
                ProductListingContent(
                    modifier = Modifier
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
            rememberNavController()
        )
    }
}