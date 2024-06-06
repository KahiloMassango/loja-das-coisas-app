package com.example.store.presentation.screens.product_detail.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.presentation.common.StarRating

@Composable
fun ProductDetailsSection(
    modifier: Modifier = Modifier,
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
                text = "H&M",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "2.850kz",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Short black dress",
            color = MaterialTheme.colorScheme.inverseOnSurface,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Light
        )
        StarRating(
            totalRatings = 23, rating = 3
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.product_description_text),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Normal
        )
    }
}