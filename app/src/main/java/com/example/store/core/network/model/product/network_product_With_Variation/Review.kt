package com.example.store.core.network.model.product.network_product_With_Variation


data class Review(
    val customerImage: String?,
    val customerName: String,
    val id: Int,
    val rating: Double,
    val reviewText: String?
)