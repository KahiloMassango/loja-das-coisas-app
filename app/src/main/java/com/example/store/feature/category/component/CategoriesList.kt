package com.example.store.feature.category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.category.model.Category
import com.example.store.feature.category.model.womenCategories

@Composable
internal fun CategoriesList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryClick: (String) -> Unit
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