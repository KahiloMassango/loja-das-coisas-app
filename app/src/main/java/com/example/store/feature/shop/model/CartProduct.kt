package com.example.store.feature.shop.model

data class CartProduct(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val color: String,
    val size: String,
    val quantity: Int,
)

val cartProduct = CartProduct(
    id = "ggggg",
    name = "T-Shirt Sailing",
    price = 2500.0,
    imageUrl = "",
    color = "Preto",
    size = "XL",
    quantity = 1
)
