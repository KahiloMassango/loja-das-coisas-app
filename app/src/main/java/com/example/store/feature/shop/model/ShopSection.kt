package com.example.store.feature.shop.model


enum class ShopSection(
    val description: String
) {
    Women("Mulheres"),
    Men("Homens"),
    Kids("Crianças")
}



val shopSections = listOf(
    Pair(ShopSection.Women, "Mulheres"),
    Pair(ShopSection.Men, "Homens"),
    Pair(ShopSection.Kids, "Crianças"),
)

