package com.example.store.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.my_orders.component.OrdersContentList


@Composable
fun OrdersContentLoadingState(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        repeat(6) {
            item {
                OrderItemCardSkeleton()
            }
        }
    }
}


@Composable
fun OrderItemCardSkeleton(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp), // Apply shimmer effect
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Placeholder for the Order number and date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 100.dp, height = 16.dp)
                )
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 80.dp, height = 16.dp)
                )
            }

            // Placeholder for the item count and total amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 120.dp, height = 16.dp)

                )
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 100.dp, height = 16.dp)

                )
            }

            // Placeholder for the button and delivery status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 80.dp, height = 32.dp),
                    cornerShape = 50

                )
                ShimmerBox(
                    modifier = Modifier
                        .size(width = 60.dp, height = 16.dp)
                )
            }
        }
    }
}


@Composable
fun ProductCard1(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(10) {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        OrdersContentList(onDetailClick = {})
    }
}

@Preview
@Composable
private fun Preview1() {
    StoreTheme {
        OrdersContentLoadingState()
        //OrderItemCardSkeleton()
    }
}