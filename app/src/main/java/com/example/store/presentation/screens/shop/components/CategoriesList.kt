package com.example.store.presentation.screens.shop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.ThemePreviews
import com.example.store.ui.theme.StoreTheme

@Composable
internal fun CategoriesList(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        categories.forEach { category ->
            CategoryCard(
                category = category,
                onCategoryClick = { onCategoryClick(it) }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CategoriesList(
            onCategoryClick = {},
            categories = womenCategories
            )
    }
}