package com.example.store.feature.product_detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    onStoreClick: () -> Unit,
    name: String,
    description: String,
    storeName: String,
) {
    Column(
        modifier = modifier
    ) {

        Text(
            modifier = Modifier.clickable { onStoreClick() },
            text = storeName,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,

            )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier,
            text = name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3
        )
    }
}


