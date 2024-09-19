package com.example.store.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.data.mock.p1
import com.example.store.core.model.Product
import com.example.store.core.ui.theme.StoreTheme

@Composable
private fun ProductCard1(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(164.dp),
        onClick = { onClick(product.id) },
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp)
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
                    painter = painterResource(id = R.drawable.men_clothes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // FavoriteButton sobre a imagem, alinhado ao canto inferior direito.
                FavoriteButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)  // Alinha ao canto inferior direito.
                        .padding(8.dp),              // Espaçamento para o interior da Box.
                    isFavorite = false,
                    onClick = { onFavoriteClick(product.id) }
                )
            }

            StarRating(
                modifier = Modifier.padding(vertical = 0.dp),
                totalRatings = product.totalRating,
                rating = product.averageRating
            )
            Text(
                text = product.storeName,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = product.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${product.price}kz",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductCard1(
            onClick = {},
            product = p1,
        )
    }
}