package com.example.store.presentation.screens.shop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.presentation.common.ThemePreviews
import com.example.store.ui.theme.StoreTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CategoryCard(
    category: Category,
    onCategoryClick: (category: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = { onCategoryClick(category.title) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = category.description,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Image(
                modifier = Modifier
                    .height(110.dp)
                    .weight(1f),
                painter = painterResource(id = category.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CategoryCard(
            onCategoryClick = {},
            category = Category("",description = "Category 1", image = R.drawable.category))
    }
}