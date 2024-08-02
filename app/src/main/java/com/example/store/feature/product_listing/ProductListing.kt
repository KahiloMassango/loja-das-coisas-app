package com.example.store.feature.product_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.shop.component.ProductGrid

@Composable
fun ProductListing(
    modifier: Modifier = Modifier,
    viewModel: ProductListingViewModel = hiltViewModel(),
    title: String,
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = title,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        //contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ProductGrid(
                    onProductClick = { onProductClick(it) },
                    onSort = {},
                    products = emptyList()
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {

    }
}
