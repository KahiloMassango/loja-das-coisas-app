package com.example.store.core.model.product


data class ProductItem(
    val id: String,
    val color: String?,
    val image: String?,
    val price: Double,
    val size: String?,
    val stockQuantity: Int
)