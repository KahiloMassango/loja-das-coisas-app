package com.example.store.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Badge
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
import com.example.store.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.detail_image_ex2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                StarRating(
                    modifier = Modifier.padding(vertical = 3.dp),
                    totalRatings = 56,
                    rating = 2
                )
                Text(
                    text = "Dorothy Perkins",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "T-Shirt SPANISH",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "2.000kz",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Badge(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "-20%",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            FavoriteButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 55.dp),
                isFavorite = false,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        shadowElevation = 5.dp,
        color = if (isFavorite) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
        contentColor = if (isFavorite) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .padding(4.dp),
            imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            //tint = MaterialTheme.colorScheme.inverseSurface
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
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (starIndex in 1..5) {
            val isFilled = starIndex <= rating
            val starIcon = if (isFilled) R.drawable.filled_star else R.drawable.outlined_star
            val starColor =
                if (isFilled) Color(0xFFFFBA49) else MaterialTheme.colorScheme.onSurfaceVariant

            Icon(
                modifier = Modifier
                    .padding(end = 3.dp)
                    .size(14.dp),
                painter = painterResource(id = starIcon),
                contentDescription = "Star $starIndex",
                tint = starColor
            )
        }
        Text(
            text = "($totalRatings)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        )
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        /*StarRate(
            totalRatings = 15,
            rating = 2
        )*/
        ProductCard(
            onClick = {}
        )
    }
}