package com.example.store.presentation.screens.shop.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.StoreCenteredTopBar

@Composable
fun ProductListingContent(
    title: String,
    category: String,
    filterList: List<Filter>,
    onProductClick: () -> Unit,
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
                ProductListingGrid(
                    modifier = Modifier,
                    onProductClick = { onProductClick() }
                )
            }
        }
    }
}
