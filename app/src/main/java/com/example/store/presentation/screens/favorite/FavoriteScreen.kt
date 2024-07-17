package com.example.store.presentation.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.component.StoreLargeTopBar
import com.example.store.presentation.screens.shop.component.SortOption
import com.example.store.presentation.screens.shop.component.SortOptionContainer
import com.example.store.presentation.screens.shop.component.SortingHeader
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onProductDetail: (Int) -> Unit,
) {
    var selectedOption by remember { mutableStateOf(SortOption.Popular.title) }
    var isSortingOptionOpen by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (isSortingOptionOpen) {
        SortOptionContainer(
            state = bottomSheetState,
            selectedOption = selectedOption,
            onOptionClick = {
                selectedOption = it
                coroutineScope.launch {
                    delay(200)
                    bottomSheetState.hide()
                    //delay(100)
                }.invokeOnCompletion { isSortingOptionOpen = false }
            },
            onDismissRequest = { isSortingOptionOpen = false }
        )
    }

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
                Surface(
                    modifier = modifier
                        .zIndex(1f)
                        .fillMaxWidth(),
                    shadowElevation = 5.dp,
                ) {
                    Box {
                        SortingHeader(
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                            onSortClick = { isSortingOptionOpen = true }
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(0.dp)) }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { FavoriteProductCard() }
                    item { Spacer(modifier = Modifier.height(0.dp)) }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StoreTheme {
        FavoriteScreen(){}
    }
}
