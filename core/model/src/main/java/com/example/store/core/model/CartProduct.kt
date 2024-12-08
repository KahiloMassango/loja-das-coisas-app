package com.example.store.core.model

data class CartProduct(
    val productItemId: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val color: String?,
    val size: String?,
    val quantity: Int,
)

val cartProduct = CartProduct(
    productItemId = "11",
    name = "T-Shirt Sailing",
    price = 2500.0,
    imageUrl = "",
    color = "Preto",
    size = "XL",
    quantity = 1
)

