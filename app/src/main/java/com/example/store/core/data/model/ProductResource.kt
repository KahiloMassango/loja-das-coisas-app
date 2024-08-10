package com.example.store.core.data.model

import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.model.CartProduct
import com.example.store.core.model.Product

fun Product.asFavoriteProductEntity(
    color: String,
    size: String
): FavoriteProductEntity = FavoriteProductEntity(
    id = id,
    name = name,
    storeName = storeName,
    price = price,
    imageUrl = images[0],
    color = color,
    size = size,
    avgRating = averageRating,
    totalRatings = totalRating

)

fun Product.asCartProductEntity(
    color: String,
    size: String
): CartProductEntity = CartProductEntity(
    uuid = id,
    name = name,
    price = price,
    imageUrl = images[0],
    color = color,
    size = size,
    quantity = 1
)