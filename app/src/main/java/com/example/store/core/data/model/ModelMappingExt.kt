package com.example.store.core.data.model

import com.example.store.core.database.model.AddressEntity
import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.model.Address
import com.example.store.core.model.CartProduct
import com.example.store.core.model.product.Product



fun Product.asCartProduct(
    color: String,
    size: String,
    price: Double = 9.0
): CartProduct = CartProduct(
    uuid = id.toString(),
    name = title,
    price = price,
    imageUrl = image,
    color = color,
    size = size,
    quantity = 1,
)

fun CartProduct.asEntity(): CartProductEntity = CartProductEntity(
    id = id,
    uuid = uuid,
    name = name,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    quantity = 1
)

fun Address.asEntity(): AddressEntity = AddressEntity(
    id = id,
    receiverName = receiverName,
    phoneNumber = phoneNumber,
    addressType = addressType,
    addressLine = addressLine.address,
    shortName = addressLine.shortName,
    latitude = latitude,
    longitude = longitude
)
