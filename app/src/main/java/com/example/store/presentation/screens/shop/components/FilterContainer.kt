package com.example.store.presentation.screens.shop.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.ThemePreviews
import com.example.store.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterContainer(
    filters: List<Filter>,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        filters.forEachIndexed { index, filter ->
            FilterChip(
                selected = selected == index,
                onClick = {
                    onFilterSelected(filter.name)
                    selected = index
                          },
                label = { Text(text = filter.description) },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    labelColor = MaterialTheme.colorScheme.onBackground,
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = MaterialTheme.colorScheme.background,
                    selectedBorderColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}



@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        FilterContainer(
            onFilterSelected = {},
            filters = filterList
        )
    }
}
