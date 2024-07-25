package com.example.store.presentation.screens.reviews.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.ui.theme.StoreTheme


@Composable
fun RatingChart(
    modifier: Modifier = Modifier,
    totalRatings: Int,
    ratings: List<Int>
) {
    Column(
        modifier = modifier,
    ) {
        ratings.forEachIndexed { index, rating ->

            val percentage = (rating.toFloat() / totalRatings).coerceIn(0.03f, 1f)

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Row(
                    modifier =  Modifier.weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        repeat(5 - index) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(14.dp),
                                painter = painterResource(id = R.drawable.filled_star),
                                contentDescription = null,
                                tint = Color(0xFFFFBA49)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(percentage)
                                .height(5.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(50)
                                )
                        )
                    }
                }
                Text(
                    modifier = Modifier.weight(0.3f),
                    text = rating.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        RatingChart(
            totalRatings = 100,
            ratings = listOf(5, 25, 15, 5, 50)
        )
    }
}