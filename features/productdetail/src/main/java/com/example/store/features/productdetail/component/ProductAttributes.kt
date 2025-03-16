package com.example.store.features.productdetail.component

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
import com.example.store.core.model.Category
import com.example.store.core.ui.component.FavoriteButton

@Composable
internal fun ProductAttributes(
    category: Category,
    selectedSize: String?,
    selectedColor: String?,
    onShowSizeOptions: () -> Unit,
    onShowColorOptions: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            category.hasSizeVariation && category.hasColorVariation -> {
                // Show both Color and Size dropdowns
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Attribute(
                        modifier = Modifier.weight(1f),
                        label = "Cor",
                        selectedOption = selectedColor,
                        onClick = onShowColorOptions
                    )
                    Attribute(
                        modifier = Modifier.weight(1f),
                        label = "Tamanho",
                        selectedOption = selectedSize,
                        onClick = onShowSizeOptions
                    )
                }
                FavoriteButton(
                    modifier = Modifier,
                    isFavorite = false,
                    onClick = {}
                )
            }

            category.hasColorVariation -> {
                // Show only Color dropdown
                Attribute(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    selectedOption = selectedColor,
                    onClick = onShowColorOptions
                )
            }

            category.hasSizeVariation -> {
                // Show only Size dropdown
                Attribute(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    selectedOption = selectedSize,
                    onClick = onShowSizeOptions
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Attribute(
    label: String,
    selectedOption: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
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
                text = selectedOption?.let { "$label: $selectedOption" } ?: "Selecione $label",
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Show size options" // Add content description
            )
        }
    }
}
