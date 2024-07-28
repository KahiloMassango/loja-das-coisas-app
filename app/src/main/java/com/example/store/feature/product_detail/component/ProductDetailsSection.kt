package com.example.store.feature.product_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StarRating

@Composable
fun ProductDetailsSection(
    modifier: Modifier = Modifier,
    productName: String,
    storeName: String,
    price: String,
    totalRating: Int,
    averageRating: Int,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = storeName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = price,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = productName,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Light
        )
        StarRating(
            totalRatings = totalRating, rating = averageRating
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Normal
        )
    }
}