package com.example.store.feature.shop.model


enum class ShopSection { Women, Men, Kids }

fun ShopSection.getSectionTitle(): String = sectionTitles[this]!!

private val sectionTitles = mapOf(
    ShopSection.Women to "Mulheres",
    ShopSection.Men to "Homens",
    ShopSection.Kids to "Crianças"
)

val shopSections = listOf(
    Pair(ShopSection.Women, "Mulheres"),
    Pair(ShopSection.Men, "Homens"),
    Pair(ShopSection.Kids, "Crianças"),
)

