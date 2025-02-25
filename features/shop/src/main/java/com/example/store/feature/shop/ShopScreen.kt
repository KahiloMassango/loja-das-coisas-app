package com.example.store.feature.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.product.Product
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.feature.shop.component.FilterContainer
import com.example.store.feature.shop.component.ProductsGrid
import com.example.store.feature.shop.component.ShopLoadingScreen
import com.example.store.feature.shop.model.Filter
import com.example.store.feature.shop.model.Gender


@Composable
internal fun ShopScreen(
    viewModel: ShopViewModel = hiltViewModel(),
    gender: String,
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val filters = viewModel.filters

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = Gender.entries.find { it.description == gender }!!.description,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        when (uiState) {
            is ShopUiState.Loading -> ShopLoadingScreen(Modifier.padding(paddingValues))
            is ShopUiState.Error -> ErrorScreen(onTryAgain = {     })
            is ShopUiState.Success -> ShopContent(
                modifier = Modifier.padding(paddingValues),
                currentFilter = uiState.filter,
                orderBy = uiState.orderBy,
                products = uiState.products,
                filters = filters,
                onFilterChange = { viewModel.updateFilter(it) },
                onNavigateUp = onNavigateUp,
                onProductClick = { onProductClick(it) },
                onChangeOrderOption = { viewModel.updateOrderOption(it) },
            )
        }
    }
}

@Composable
private fun ShopContent(
    modifier: Modifier = Modifier,
    currentFilter: String,
    orderBy: String,
    products: List<Product>,
    filters: List<Filter>,
    onFilterChange: (String) -> Unit,
    onProductClick: (String) -> Unit,
    onChangeOrderOption: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FilterContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                filters = filters,
                currentFilter = currentFilter,
                onSelectFilter = {
                    onFilterChange(it)
                }
            )
            ProductsGrid(
                modifier = Modifier,
                selectedOption = orderBy,
                onChangeOrderOption = { onChangeOrderOption(it) },
                onProductClick = { onProductClick(it) },
                products = products,
            )

        }
    }
}

