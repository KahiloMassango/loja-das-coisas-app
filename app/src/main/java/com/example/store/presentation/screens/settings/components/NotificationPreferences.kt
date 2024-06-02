package com.example.store.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun NotificationPreferences(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        NotificationPreferenceOption(
            description = "Promoções",
            checked = false,
            onCheckedChange = { }
        )
        NotificationPreferenceOption(
            description = "Novos produtos",
            checked = false,
            onCheckedChange = { }
        )
        NotificationPreferenceOption(
            description = "Geral",
            checked = true,
            onCheckedChange = { }
        )
    }
}
