package com.example.store.feature.category.model

import androidx.annotation.DrawableRes
import com.example.store.R


enum class Section(
    val description: String,
    @DrawableRes
    val icon: Int
) {
    Women("Mulheres", R.drawable.ic_women_category),
    Men("Homens", R.drawable.ic_man_category),
    Kids("Crianças", R.drawable.ic_kids_category),
    Cosmetics("Cosméticos", R.drawable.ic_cosmetics_category)
}
