package com.example.store.feature.shop.model

data class ShopScreenUiState(
    val content: ShopScreenContent = ShopScreenContent.Categories,
    val section: ShopSection = ShopSection.Women,
    val category: String = ""
)
