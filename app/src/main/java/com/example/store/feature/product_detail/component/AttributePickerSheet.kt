package com.example.store.feature.product_detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomDragHandle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributePickerSheet(
    state: SheetState,
    selectorDescription: String,
    selectedAttribute: String,
    attributes: List<String>,
    onSelectAttribute: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = state,
        modifier = modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle(selectorDescription)
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(attributes) { attribute ->
                AttributeSheetItem(
                    item = attribute,
                    selected = attribute == selectedAttribute,
                    onClick = {
                        onSelectAttribute(attribute)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AttributeSheetItem(
    item: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if(selected) MaterialTheme.colorScheme.primary else
        MaterialTheme.colorScheme.secondaryContainer
    val contentColor = if(selected) MaterialTheme.colorScheme.onPrimary else
        MaterialTheme.colorScheme.onSecondaryContainer
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 8.dp),
            text = item,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}