package com.example.store.features.discover.category.model

import androidx.annotation.DrawableRes
import com.example.store.core.ui.R


internal data class SubCategory(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int
)

internal val womenCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.category),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.category),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

internal val menCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.men_clothes),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.men_shoes),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

internal val kidsCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.category),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.category),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

internal val cosmeticsCategories = listOf(
    SubCategory(name = "skincare", description = "Skin Care", image = R.drawable.category),
    SubCategory(name = "makeup", description = "Makeup", image = R.drawable.category),
    SubCategory(name = "bodycare", description = "Cuidados do crpo", image = R.drawable.category),
    SubCategory(name = "fragrance", description = "Perfumes", image = R.drawable.category)
)

internal fun getSubCategories(category: Category): List<SubCategory> {
    return when (category) {
        Category.Women -> womenCategories
        Category.Men -> menCategories
        Category.Kids -> kidsCategories
        Category.Cosmetics -> cosmeticsCategories
    }
}

