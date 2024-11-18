package com.example.store.feature.favorite.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.model.FavoriteProduct
import com.example.store.core.model.favoriteProduct
import com.example.store.core.ui.component.AttributeDescription
import com.example.store.core.ui.component.ProductRating
import com.example.store.core.ui.theme.StoreTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteProductCard(
    modifier: Modifier = Modifier,
    product: FavoriteProduct,
    onProductClick: (String) -> Unit,
    onRemoveFavorite: (String) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        onClick = { onProductClick(product.id) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

            ) {
            Image(
                painter = painterResource(id = R.drawable.detail_image_ex1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(104.dp)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                    Text(
                        text = product.storeName,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        text = product.name,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AttributeDescription(attribute = "Cor", value = product.color)
                        AttributeDescription(attribute = "Tamanho", value = favoriteProduct.size)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(36.dp)
                    ) {
                        Text(
                            text = "${product.price}kz",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        ProductRating(totalRatings = product.totalRatings, rating = product.avgRating)
                    }
                }

                IconButton(
                    modifier = Modifier.size(22.dp),
                    onClick = { onRemoveFavorite(product.id) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        FavoriteProductCard(
            product = favoriteProduct,
            onProductClick = {},
            onRemoveFavorite = { }
        )
    }
}