package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.cart.CartProduct

@Entity(tableName = "cart")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = false)
    val productItemId: String,
    val name: String,
    val price: Double,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val color: String?,
    val size: String?,
    val quantity: Int = 1,
)

fun CartProductEntity.asExternalModel() = CartProduct(
    productItemId = productItemId,
    name = name,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    quantity = quantity
)
