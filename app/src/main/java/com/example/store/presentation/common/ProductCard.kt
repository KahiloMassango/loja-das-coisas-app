package com.example.store.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp),
                painter = painterResource(id = R.drawable.men_clothes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(4.dp))
            StarRate(
                modifier = Modifier,
                totalRatings = 10,
                rating = 3
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Mango Boy",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "T-Shirt Sailing",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "2.000kz",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun StarRate(
    modifier: Modifier = Modifier,
    totalRatings: Int,
    rating: Int

){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..5) {
            val drawableRes = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarOutline
            val color = if(i <= rating) Color(0xFFFFBA49) else MaterialTheme.colorScheme.onSurfaceVariant
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = drawableRes,
                contentDescription = null,
                tint = color
            )
        }
        Text(
            text = "($totalRatings)",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.inverseOnSurface
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