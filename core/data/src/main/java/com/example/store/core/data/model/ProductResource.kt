package com.example.store.core.data.model

import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.model.product.ProductItem

fun ProductItem.asCartProductEntity(
    productName: String,
    imageUrl: String
): CartProductEntity = CartProductEntity(
    productItemId = id,
    name = productName,
    price = price,
    imageUrl = image ?: imageUrl,
    color = color,
    size = size,
)
