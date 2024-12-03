package com.example.store.core.model.product


data class ProductWithVariation(
    val id: String,
    val description: String,
    val image: String,
    val storeId: String,
    val subCategory: String,
    val storeName: String,
    val title: String,
    val productItems: List<ProductItem>
)