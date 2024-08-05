package com.example.store.feature.shop.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Product
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.feature.shop.model.Filter
import com.example.store.feature.shop.model.ShopSection

@Composable
fun ShopListingScreen(
    modifier: Modifier = Modifier,
    currentFilter: String,
    orderBy: String,
    section: ShopSection,
    category: String,
    products: List<Product>,
    filters: List<Filter>,
    onFilterChange: (String) -> Unit,
    onProductClick: (String) -> Unit,
    onOrderBy: (String) -> Unit,
    onSearch: () -> Unit,
    onNavigateUp: () -> Unit,
) {

    BackHandler { onNavigateUp() }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = section.description,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
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
                        filters = filters,
                        selected = currentFilter,
                        onSelectFilter = {
                            onFilterChange(it)
                        }
                    )
                }
                ProductGrid(
                    modifier = Modifier,
                    selectedOption = orderBy,
                    onSort = { onOrderBy(it) },
                    onProductClick = { onProductClick(it) },
                    products = products,
                )
            }
        }
    }
}
