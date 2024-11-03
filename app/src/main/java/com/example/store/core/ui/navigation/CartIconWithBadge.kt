package com.example.store.core.ui.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartIconWithBadge(
    modifier: Modifier = Modifier,
    cartItemsCount: Int,
    icon: ImageVector
) {
    BadgedBox(
        modifier = modifier,
        badge = {
            Badge(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) { Text(text = "$cartItemsCount") }
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
    }
}