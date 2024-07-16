package com.example.store.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.presentation.component.ProductCard
import com.example.store.ui.theme.StoreTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    items: Int,
    onItemClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
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
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable { onViewAllClick() },
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
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }
            item { ProductCard(Modifier.width(150.dp)) {} }

        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        Section(title = "Sale", description = "Super summer sale", items = 15, onItemClick ={} ) {

        }
    }
}