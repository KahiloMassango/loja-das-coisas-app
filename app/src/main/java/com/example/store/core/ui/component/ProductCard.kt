package com.example.store.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.data.mock.p1
import com.example.store.core.model.Product
import com.example.store.core.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (productId: String) -> Unit,
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp)
                    .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
                painter = painterResource(id = R.drawable.men_clothes),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            StarRating(
                modifier = Modifier.padding(vertical = 3.dp),
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
            Text(
                text = "${product.price}kz",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun AddFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        enabled = !isFavorite,
        shape = CircleShape,
        shadowElevation = 5.dp,
        color =  if(isFavorite) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.inverseSurface,
        contentColor = if (isFavorite) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
        )
    }
}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    totalRatings: Int,
    rating: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        for (starIndex in 1..5) {
            val isFilled = starIndex <= rating
            val starIcon = if (isFilled) R.drawable.filled_star else R.drawable.outlined_star
            val starColor =
                if (isFilled) Color(0xFFFFBA49) else MaterialTheme.colorScheme.onSurfaceVariant

            Icon(
                modifier = Modifier
                    .padding(end = 3.dp)
                    .size(12.dp),
                painter = painterResource(id = starIcon),
                contentDescription = "Star $starIndex",
                tint = starColor
            )
        }
        Text(
            text = "($totalRatings)",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        )
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductCard(
            onClick = {},
            product = p1,
        )
    }
}