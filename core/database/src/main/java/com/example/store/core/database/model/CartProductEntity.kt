package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.CartProduct

@Entity(tableName = "cart")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uuid: String,
    val name: String,
    val price: Double,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val color: String,
    val size: String,
    val quantity: Int,
)

fun CartProductEntity.asExternalModel() = CartProduct(
    id = id,
    uuid = uuid,
    name = name,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    quantity = quantity
)
