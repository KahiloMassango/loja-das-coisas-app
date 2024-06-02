package com.example.store.presentation.screens.shop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.ProductCard
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListingContent(
    modifier: Modifier = Modifier,

) {
    var showOrderByOptions by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableIntStateOf(0) }
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()


    if (showOrderByOptions){
        OrderByOptions(
            state = bottomSheetState,
            selectedOption = selectedOption,
            onOptionClick = {
                selectedOption = it
             coroutineScope.launch {
                 bottomSheetState.hide()
             }   .invokeOnCompletion { showOrderByOptions = false }
            },
            onDismissRequest = {
                showOrderByOptions = false
            }
        )
    }
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier,
                shadowElevation = 9.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    OrderByButton(
                        modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                        onClick = { showOrderByOptions = true }
                    )
                }
            }
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                repeat(15) {
                    item {
                        ProductCard(
                            modifier = Modifier,
                            onClick = { /* TODO */ }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StoreTheme {
        ProductListingContent()
    }
}