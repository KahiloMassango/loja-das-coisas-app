package com.example.store.feature.shop.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.feature.shop.component.AdvertisementCard
import com.example.store.feature.shop.component.CategoriesList
import com.example.store.feature.shop.component.ShopSectionTabs
import com.example.store.feature.shop.model.ShopSection
import com.example.store.feature.shop.model.kidsCategories
import com.example.store.feature.shop.model.menCategories
import com.example.store.feature.shop.model.womenCategories

@Composable
fun CategorySelectionScreen(
    currentSection: ShopSection,
    onSectionClick: (ShopSection) -> Unit,
    onCategoryClick: (category: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = "Categorias",
                canNavigateBack = false,
                elevation = 3.dp
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
                        }
                    }
                }
            }
        }
    }
}