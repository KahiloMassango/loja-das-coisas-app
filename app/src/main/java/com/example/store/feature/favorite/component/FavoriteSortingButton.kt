package com.example.store.feature.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.feature.favorite.model.PriceSortType

@Composable
fun FavoriteSortingButton(
    modifier: Modifier = Modifier,
    sortType: PriceSortType,
    onSortClick: (PriceSortType) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(GenericShape { size, _ ->
                lineTo(size.width, 0f)
                lineTo(size.width, Float.MAX_VALUE)
                lineTo(0f, Float.MAX_VALUE)
            })
            .shadow(8.dp)
            .background(MaterialTheme.colorScheme.surface),

        ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .clickable {
                    when(sortType) {
                        PriceSortType.Ascending -> onSortClick(PriceSortType.Descending)
                        PriceSortType.Descending -> onSortClick(PriceSortType.Ascending)
                    }
                },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(id = R.drawable.sort_icon),
                contentDescription = "Sort",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Preço: ${sortType.description}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
