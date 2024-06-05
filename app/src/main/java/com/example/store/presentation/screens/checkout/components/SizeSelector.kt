package com.example.store.presentation.screens.checkout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.CustomDragHandle
import com.example.store.presentation.screens.detail.components.SelectorItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeSelector(
    state: SheetState,
    selectedSize: String,
    sizeOptions: List<String>,
    onSelectSize: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = state,
        modifier = modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = Color.Transparent,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle("Selecionar tamanho")
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(sizeOptions) { size ->
                SelectorItem(
                    item = size,
                    selected = size == selectedSize,
                    onClick = {
                        onSelectSize(size)
                    }
                )
            }
        }
    }
}