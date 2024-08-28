package com.example.store.feature.settings.component

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
import com.example.store.feature.settings.component.NotificationPreferenceOption

@Composable
fun NotificationPreferences(
    modifier: Modifier = Modifier,
    notificationPreference: Boolean,
    onNotificationPreferenceChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        NotificationPreferenceOption(
            description = "Geral",
            checked = notificationPreference,
            onCheckedChange = { onNotificationPreferenceChange(it) }
        )
    }
}
