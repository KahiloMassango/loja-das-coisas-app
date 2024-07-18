package com.example.store.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.store.presentation.component.ProductCard1
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.shop.component.ProductGrid
import com.example.store.ui.theme.StoreTheme

@Composable
fun ProductListing(
    modifier: Modifier = Modifier,
    title: String,
    onProductClick: (Int) -> Unit,
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
                    onProductClick = { onProductClick(0) },
                    onSort = {}
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductCard1(title = "Novos")
    }
}
