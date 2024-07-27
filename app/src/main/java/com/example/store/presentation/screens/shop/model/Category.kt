package com.example.store.presentation.screens.shop.model

import androidx.annotation.DrawableRes
import com.example.store.R


data class Category(
    val title: String,
    val description: String,
    @DrawableRes
    val image: Int
)

val womenCategories = listOf(
    Category(title = "Clothes", description = "Roupas", image = R.drawable.category),
    Category(title = "Shoes", description = "Calçados", image = R.drawable.category)
)

val menCategories = listOf(
    Category(title = "Clothes", description = "Roupas", image = R.drawable.men_clothes),
    Category(title = "Shoes", description = "Calçados", image = R.drawable.men_shoes)
)

val kidsCategories = listOf(
    Category(title = "Clothes", description = "Roupas", image = R.drawable.category),
    Category(title = "Shoes", description = "Calçados", image = R.drawable.category)
)

