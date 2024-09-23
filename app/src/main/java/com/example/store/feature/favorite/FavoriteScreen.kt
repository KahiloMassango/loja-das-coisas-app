package com.example.store.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.FavoriteProduct
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.favorite.component.EmptyFavoriteScreen
import com.example.store.feature.favorite.component.FavoriteProductCard
import com.example.store.feature.favorite.component.FavoriteSortingButton

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    val sortType by viewModel.sortType.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(title = "Favoritos", canNavigateBack = false)
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                FavoriteSortingButton(
                    sortType = sortType,
                    onSortClick = { viewModel.setSorting(it) }
                )
                if(products.isEmpty()){
                    EmptyFavoriteScreen()
                } else {
                    FavoriteContent(
                        favoriteProducts = products,
                        onProductClick = { onProductClick(it) },
                        onRemoveFavorite = { viewModel.removeFavoriteProduct(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    favoriteProducts: List<FavoriteProduct>,
    onProductClick: (String) -> Unit,
    onRemoveFavorite: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        items(favoriteProducts) { product ->
            FavoriteProductCard(
                modifier = Modifier,
                product = product,
                onProductClick = { id -> onProductClick(id) },
                onRemoveFavorite = { id -> onRemoveFavorite(id)}
            )
        }
    }
}





@Preview
@Composable
private fun Preview() {
    StoreTheme {
        FavoriteScreen {}
    }
}
