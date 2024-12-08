package com.example.store.features.userprofile.orders.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun OrdersContentList(
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        repeat(14) {
            item {
                OrderItemCard(
                    modifier = Modifier.padding(bottom = 24.dp),
                    onDetailClick = onDetailClick
                )
            }
        }
    }
}
