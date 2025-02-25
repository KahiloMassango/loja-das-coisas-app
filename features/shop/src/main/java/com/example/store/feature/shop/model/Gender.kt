package com.example.store.feature.shop.model

import androidx.annotation.DrawableRes
import com.example.store.core.ui.R


internal enum class Gender(
    val description: String,
    @DrawableRes
    val icon: Int
) {
    Women("Mulher", R.drawable.ic_women_category),
    Men("Homem", R.drawable.ic_man_category),
    Kids("Criança", R.drawable.ic_kids_category),
}
