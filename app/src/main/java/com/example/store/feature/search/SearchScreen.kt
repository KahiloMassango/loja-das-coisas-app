package com.example.store.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.ui.component.LargeProductCard
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.search.components.StoreSearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val searchFieldFocusRequester = remember { FocusRequester() }
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchText = viewModel.searchQuery


    // Focus on search field when screen appears
    LaunchedEffect(Unit) {
        searchFieldFocusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) { onNavigateUp() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                StoreSearchBar(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(searchFieldFocusRequester),
                    query = searchText,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    onSearch = {
                        keyboardManager?.hide()
                        focusManager.clearFocus()
                    },
                    onClearQuery = {
                        viewModel.updateSearchQuery("")
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    contentPadding = PaddingValues(16.dp),
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