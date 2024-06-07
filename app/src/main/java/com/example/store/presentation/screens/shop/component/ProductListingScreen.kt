package com.example.store.presentation.screens.shop.component

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
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.screens.shop.model.CategorySection
import com.example.store.presentation.screens.shop.model.Filter
import com.example.store.presentation.screens.shop.model.getCategorySectionFilters
import com.example.store.presentation.screens.shop.model.getSectionTitle

@Composable
fun ProductListingScreen(
    productSection: CategorySection,
    productCategory: String,
    filterList: List<Filter>,
    onFilterChange: (String) -> Unit,
    onProductClick: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentFilter by remember {
        mutableStateOf(getCategorySectionFilters(productSection).first().name) }
    var selectedOption by remember { mutableStateOf(0) }
    BackHandler { onNavigateUp() }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = productSection.getSectionTitle(),
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
                if (productCategory != "Shoes") {
                    FilterContainer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        filters = filterList,
                        selected = currentFilter,
                        onSelectFilter = { currentFilter = it }
                    )
                }
                ProductGrid(
                    modifier = Modifier,
                    selectedOption = selectedOption,
                    onOptionClick = { selectedOption = it },
                    onProductClick = { onProductClick() }
                )
            }
        }
    }
}
