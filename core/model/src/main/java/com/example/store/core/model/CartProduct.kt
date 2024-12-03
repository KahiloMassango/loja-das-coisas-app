package com.example.store.core.model

data class CartProduct(
    val id: Int = 0,
    val uuid: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val color: String,
    val size: String,
    val quantity: Int,
)

val cartProduct = CartProduct(
    id = 11,
    uuid = "olk",
    name = "T-Shirt Sailing",
    price = 2500.0,
    imageUrl = "",
    color = "Preto",
    size = "XL",
    quantity = 1
)

