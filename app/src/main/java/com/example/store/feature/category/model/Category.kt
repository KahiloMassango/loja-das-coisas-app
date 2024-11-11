package com.example.store.feature.category.model

import androidx.annotation.DrawableRes
import com.example.store.R


data class Category(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int
)

val womenCategories = listOf(
    Category(name = "Clothes", description = "Roupas", image = R.drawable.category),
    Category(name = "Shoes", description = "Calçados", image = R.drawable.category),
    Category(name = "Accessory", description = "Acessórios", image = R.drawable.category)
)

val menCategories = listOf(
    Category(name = "Clothes", description = "Roupas", image = R.drawable.men_clothes),
    Category(name = "Shoes", description = "Calçados", image = R.drawable.men_shoes),
    Category(name = "Accessory", description = "Acessórios", image = R.drawable.category)
)

val kidsCategories = listOf(
    Category(name = "Clothes", description = "Roupas", image = R.drawable.category),
    Category(name = "Shoes", description = "Calçados", image = R.drawable.category),
    Category(name = "Accessory", description = "Acessórios", image = R.drawable.category)
)

val cosmeticsCategories = listOf(
    Category(name = "Skin Care", description = "Skin Care", image = R.drawable.category),
    Category(name = "Makeup", description = "Makeup", image = R.drawable.category),
    Category(name = "Hair care", description = "Cuidados com o cabelo", image = R.drawable.category),
    Category(name = "Frangace", description = "Perfumes", image = R.drawable.category)
)

