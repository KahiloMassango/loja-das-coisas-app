package com.example.store.core.data.model

import androidx.compose.ui.graphics.Color
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.model.Product

fun Product.asEntity(
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