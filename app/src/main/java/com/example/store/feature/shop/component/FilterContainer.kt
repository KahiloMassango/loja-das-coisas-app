package com.example.store.presentation.screens.shop.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.shop.model.Filter
import com.example.store.core.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterContainer(
    filters: List<Filter>,
    selected: String,
    onSelectFilter: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        filters.forEach{ filter ->
            FilterChip(
                    selected = selected == filter.name,
                onClick = { onSelectFilter(filter.name) },
                label = { Text(text = filter.description) },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    labelColor = MaterialTheme.colorScheme.onSecondary,
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
       /* FilterContainer(
            onSelectFilter = {},
            filters = getCategorySectionFilters(CategorySection.Kids)
        )*/
    }
}
