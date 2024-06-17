package com.example.store.presentation.screens.shop.model

data class ShopScreenUiState(
    val content: ShopScreenContent = ShopScreenContent.Categories,
    val section: ShopSection = ShopSection.Women,
    val category: String = ""
)
