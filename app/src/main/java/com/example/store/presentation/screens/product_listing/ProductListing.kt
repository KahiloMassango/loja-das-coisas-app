package com.example.store.presentation.screens.product_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.product_listing.ProductListingViewModel
import com.example.store.presentation.screens.shop.component.ProductGrid
import com.example.store.ui.theme.StoreTheme

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
                    onFavoriteClick = {},
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
