package com.example.store.feature.category

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.feature.category.component.CategoriesList
import com.example.store.feature.category.component.SectionSelectorTab
import com.example.store.feature.category.model.Section
import com.example.store.feature.category.model.kidsCategories
import com.example.store.feature.category.model.menCategories
import com.example.store.feature.category.model.womenCategories
import com.example.store.feature.shop.component.AdvertisementCard

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onSelectCategory: (section: String, category: String) -> Unit,
    onSearch: () -> Unit
) {
    var currentSection by rememberSaveable { mutableStateOf(Section.Women) }

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = "Categorias",
                canNavigateBack = false,
                elevation = 3.dp,
                action = {
                    IconButton(onClick = onSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                }
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
                SectionSelectorTab(
                    selectedSection = currentSection,
                    onSectionClick = { currentSection = it }
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
                            Section.Women -> CategoriesList(
                                categories = womenCategories,
                                onCategoryClick = { category ->
                                    onSelectCategory(currentSection.name, category)
                                }
                            )
                            Section.Men -> CategoriesList(
                                categories = menCategories,
                                onCategoryClick = { category ->
                                    onSelectCategory(currentSection.name, category)
                                }
                            )
                            Section.Kids -> CategoriesList(
                                categories = kidsCategories,
                                onCategoryClick = { category ->
                                    onSelectCategory(currentSection.name, category)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}