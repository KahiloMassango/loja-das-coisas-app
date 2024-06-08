package com.example.store.presentation.screens.shop.model

data class ShopScreenUiState(
    val content: ShopContent = ShopContent.Categories,
    val section: ShopSection = ShopSection.Women,
    val category: String = ""
)
