package com.example.store.presentation.screens.product_detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductAttributeSection(
    selectedSize: String,
    selectedColor: String,
    showSizeOptions: () -> Unit,
    showColorOptions: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AttributeSelector(
            modifier = Modifier.weight(1f),
            attribute = "Tamanho",
            selectedAttribute = selectedSize,
            onClick = showSizeOptions
        )
        AttributeSelector(
            modifier = Modifier.weight(1f),
            attribute = "Cor",
            selectedAttribute = selectedColor,
            onClick = showColorOptions
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AttributeSelector(
    attribute: String,
    selectedAttribute: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedCard(
        modifier = modifier ,
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$attribute: $selectedAttribute",
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Show size options" // Add content description
            )
        }
    }
}
