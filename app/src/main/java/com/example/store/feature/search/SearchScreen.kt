package com.example.store.feature.search

import android.text.style.SuggestionSpan
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var isSearchActive by remember { mutableStateOf(false) }
    val uiState by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchText = viewModel.searchQuery

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StoreSearchBar(
            modifier = Modifier
                .padding(top = 12.dp)
                .focusRequester(focusRequester),
            query = searchText,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onSearch = {
                keyboardManager?.hide()
                focusManager.clearFocus()
            },
            active = true,
            onActiveChange = { isSearchActive = it },
            onClearQuery = {
                viewModel.updateSearchQuery("")
                focusRequester.requestFocus()
            },
            onNavigateUp = {
                onNavigateUp()
            /* TODO */
            }
        ) {
            when {
                uiState.isEmpty() and searchText.isNotEmpty() -> SearchEmptyState(message = "Nada Encontrado")
                else /*uiState.isNotEmpty()*/ -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
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

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onNavigateUp: () -> Unit,
    onClearQuery: () -> Unit,
    active: Boolean,
    content: @Composable () -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onSearch(it) },
        active = active,
        onActiveChange = { onActiveChange(it) },
        modifier = modifier,
        placeholder = {
            Text(text = "Search")
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ),
        leadingIcon = {
            if (active) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        },
        trailingIcon = {
            if (query.isNotEmpty() && active) {
                IconButton(onClick = onClearQuery) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            }
        },
        content = { content() }
    )
}

@Composable
fun SearchEmptyState(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = message,
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