package com.example.store.features.productdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun AddToCartContainer(
    modifier: Modifier = Modifier,
    price: Double,
    onAddToCart: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .height(70.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.55f)
                    .background(MaterialTheme.colorScheme.primary.copy(0.9f)),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ){
                    Text(
                        modifier = Modifier,
                        text = "$price Kz",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        modifier = Modifier,
                        text = "Unidade",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.7f)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.45f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { onAddToCart() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "Add carrinho",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}