package com.example.store.features.store.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun StoreHeader(
    modifier: Modifier = Modifier,
    currentSection: StoreSection,
    onChangeSection: (StoreSection) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StoreBasicDetailCard()
        StoreSectionTabRow(
            modifier = Modifier.fillMaxWidth(),
            currentSection = currentSection,
            onChangeSection = { onChangeSection(it) }
        )
    }
}