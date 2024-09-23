package com.example.store.feature.category.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.feature.category.model.Section

@Composable
internal fun SectionSelectorTab(
    selectedSection: Section,
    onSectionClick: (Section) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedSection.ordinal,
        containerColor = MaterialTheme.colorScheme.inverseOnSurface.copy(0.1f),
        contentColor = MaterialTheme.colorScheme.onBackground,
        divider = {}
    ) {
        Section.entries.forEachIndexed { index, section ->
            Tab(
                selected = selectedSection.ordinal == index,
                onClick = {
                    onSectionClick(section)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 14.dp),
                    text = section.description,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if(selectedSection.ordinal == index) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}