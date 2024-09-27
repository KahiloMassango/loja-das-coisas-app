package com.example.store.feature.search

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.ui.component.LargeProductCard
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.search.components.SearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val uiState by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchText = viewModel.searchQuery


    Scaffold(
        modifier = modifier,
        topBar = {
            SearchTopBar(
                modifier = Modifier,
                query = searchText,
                onQueryChange = { viewModel.updateSearchQuery(it) },
                onNavigateUp = onNavigateUp
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                uiState.isEmpty() and searchText.isNotEmpty() -> SearchEmptyState(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures { focusManager.clearFocus() }
                    },
                )

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { focusManager.clearFocus() }
                            },
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        items(uiState) { product ->
                            LargeProductCard(
                                product = product,
                                onClick = { onProductClick(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun SearchEmptyState(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Nada Encontrado",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Try adjusting your search",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        SearchScreen(
            onProductClick = { },
            onNavigateUp = {}
        )
    }
}