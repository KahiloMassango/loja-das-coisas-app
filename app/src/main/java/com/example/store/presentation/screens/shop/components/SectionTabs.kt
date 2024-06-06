package com.example.store.presentation.screens.shop.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ShopSectionTabs(
    selectedSection: CategorySection,
    onTabClick: (CategorySection) -> Unit,
    modifier: Modifier = Modifier,
) {

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedSection.ordinal,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        divider = {}
    ) {
        storeSections.forEachIndexed { index,section ->
            Tab(
                selected = selectedSection.ordinal == index,
                onClick = {
                    onTabClick(section.first)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 14.dp),
                    text = section.second,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if(selectedSection.ordinal == index) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}