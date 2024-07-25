package com.example.store.core.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val section: String,
    val category: String,
    val images: List<String>,
    val storeId: String,
    val storeName: String = "Any Store",
    val availableColors: List<String>,
    val availableSizes: List<String>,
    val averageRating: Int,
    val totalRating: Int,
    val publishedAt: String
)