package com.example.store.presentation.screens.reviews.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.store.ui.theme.StoreTheme

@Composable
fun RatingStats(
    modifier: Modifier = Modifier,
    totalRatings: Int,
    ratings: List<Int>
) {
    Row(
        modifier = modifier
    ) {
        RatingAverage(
            modifier = Modifier.weight(1f),
            average = ratings.average().coerceAtMost(5.0),
            totalRatings = totalRatings
        )
        RatingChart(
            modifier = Modifier.weight(2f),
            totalRatings = totalRatings,
            ratings = ratings
        )
    }

}


@Composable
private fun RatingAverage(
    modifier: Modifier = Modifier,
    average: Double,
    totalRatings: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = average.toString(),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "$totalRatings avaliações",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        RatingStats(
            totalRatings = 23,
            ratings = listOf(12, 23, 4, 2, 0)
        )
    }
}