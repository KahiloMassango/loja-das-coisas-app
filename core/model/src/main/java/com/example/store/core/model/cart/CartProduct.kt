package com.example.store.core.model.cart

data class CartProduct(
    val productItemId: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val color: String?,
    val size: String?,
    val quantity: Int,
    val stockQuantity: Int
)

val cartProduct = CartProduct(
    productItemId = "11",
    name = "T-Shirt Sailing",
    price = 2500,
    imageUrl = "",
    color = "Preto",
    size = "XL",
    quantity = 1,
    stockQuantity = 9
)

