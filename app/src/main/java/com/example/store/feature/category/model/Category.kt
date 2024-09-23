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
    Category(name = "Shoes", description = "Calçados", image = R.drawable.category)
)

val menCategories = listOf(
    Category(name = "Clothes", description = "Roupas", image = R.drawable.men_clothes),
    Category(name = "Shoes", description = "Calçados", image = R.drawable.men_shoes)
)

val kidsCategories = listOf(
    Category(name = "Clothes", description = "Roupas", image = R.drawable.category),
    Category(name = "Shoes", description = "Calçados", image = R.drawable.category)
)

