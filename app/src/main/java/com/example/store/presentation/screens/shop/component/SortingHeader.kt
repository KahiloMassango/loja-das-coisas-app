package com.example.store.presentation.screens.shop.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.ui.theme.StoreTheme

@Composable
fun SortingHeader(
    modifier: Modifier = Modifier,
    onSortClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onSortClick() },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(id = R.drawable.sort_icon),
            contentDescription = "Sort",
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Ordenar por",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StoreTheme {
        SortingHeader {

        }
    }
}
