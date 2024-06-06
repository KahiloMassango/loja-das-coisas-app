package com.example.store.presentation.screens.shop.components


enum class CategorySection { Women, Men, Kids }

fun CategorySection.getSectionTitle(): String = sectionTitles[this]!!

private val sectionTitles = mapOf(
    CategorySection.Women to "Mulheres",
    CategorySection.Men to "Homens",
    CategorySection.Kids to "Crianças"
)

val storeSections = listOf(
    Pair(CategorySection.Women, "Mulheres"),
    Pair(CategorySection.Men, "Homens"),
    Pair(CategorySection.Kids, "Crianças"),
)

