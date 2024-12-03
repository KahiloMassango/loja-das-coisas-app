package com.example.store.feature.discover.model

import androidx.annotation.DrawableRes
import com.example.store.R


data class SubCategory(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int
)

val womenCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.category),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.category),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

val menCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.men_clothes),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.men_shoes),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

val kidsCategories = listOf(
    SubCategory(name = "clothes", description = "Roupas", image = R.drawable.category),
    SubCategory(name = "shoes", description = "Calçados", image = R.drawable.category),
    SubCategory(name = "accessories", description = "Acessórios", image = R.drawable.category)
)

val cosmeticsCategories = listOf(
    SubCategory(name = "skincare", description = "Skin Care", image = R.drawable.category),
    SubCategory(name = "makeup", description = "Makeup", image = R.drawable.category),
    SubCategory(name = "bodycare", description = "Cuidados do crpo", image = R.drawable.category),
    SubCategory(name = "fragrance", description = "Perfumes", image = R.drawable.category)
)

fun Category.getSubCategories(): List<SubCategory> {
    return when (this) {
        Category.Women -> womenCategories
        Category.Men -> menCategories
        Category.Kids -> kidsCategories
        Category.Cosmetics -> cosmeticsCategories
    }
}

