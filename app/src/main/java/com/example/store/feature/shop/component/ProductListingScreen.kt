package com.example.store.feature.shop.component

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Product
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.screens.shop.component.FilterContainer
import com.example.store.presentation.screens.shop.component.SortOption
import com.example.store.presentation.screens.shop.model.ShopSection
import com.example.store.presentation.screens.shop.model.getSectionFilters
import com.example.store.presentation.screens.shop.model.getSectionTitle

@Composable
fun ProductListingScreen(
    modifier: Modifier = Modifier,
    section: ShopSection,
    category: String,
    products: List<Product>,
    onFilterChange: (String) -> Unit,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    var currentFilter by remember {
        mutableStateOf(getSectionFilters(section).first().name) }
    var selectedOption by remember { mutableStateOf(SortOption.Popular.title) }
    BackHandler { onNavigateUp() }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = section.getSectionTitle(),
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
                modifier = Modifier.fillMaxSize()
            ) {
                if (category != "Shoes") {
                    FilterContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        filters = getSectionFilters(section),
                        selected = currentFilter,
                        onSelectFilter = {
                            onFilterChange(it)
                            currentFilter = it
                        }
                    )
                }
                ProductGrid(
                    modifier = Modifier,
                    selectedOption = selectedOption,
                    onSort = { selectedOption = it },
                    onProductClick = { onProductClick(it) },
                    products = products,
                    onFavoriteClick = { onFavoriteClick(it) }
                )
            }
        }
    }
}
