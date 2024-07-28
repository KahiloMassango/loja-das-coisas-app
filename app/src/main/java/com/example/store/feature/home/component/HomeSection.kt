package com.example.store.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Product
import com.example.store.core.ui.component.ProductCard
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    products: List<Product>,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    onSeeAll: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable { onSeeAll(title) },
                text = "Ver tudo",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(it) },
                    onFavoriteClick = { onFavoriteClick(it) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        Section(
            title = "Sale",
            description = "Super summer sale",
            onProductClick = {},
            onFavoriteClick = {},
            onSeeAll = {},
            products = emptyList()
        )
    }
}