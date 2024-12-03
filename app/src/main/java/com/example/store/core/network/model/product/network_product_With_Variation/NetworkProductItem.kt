package com.example.store.core.network.model.product.network_product_With_Variation

import com.example.store.core.model.product.ProductItem


data class NetworkProductItem(
    val id: String,
    val color: String?,
    val image: String?,
    val price: Double,
    val size: String?,
    val stockQuantity: Int
)

fun NetworkProductItem.asExternalModel(): ProductItem =
    ProductItem(
        id = id,
        color = color,
        image = image,
        price = price,
        size = size,
        stockQuantity = stockQuantity
    )