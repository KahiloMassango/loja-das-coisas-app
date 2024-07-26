package com.example.store.presentation.screens.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationPreferences(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        var bb by remember { mutableStateOf(false) }
        NotificationPreferenceOption(
            description = "Promoções",
            checked = false,
            onCheckedChange = { }
        )
        NotificationPreferenceOption(
            description = "Novos produtos",
            checked = true,
            onCheckedChange = { }
        )
        NotificationPreferenceOption(
            description = "Geral",
            checked = bb,
            onCheckedChange = { bb = it }
        )
    }
}
