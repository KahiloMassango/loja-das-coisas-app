package com.example.store.core.network.model.product.network_product

import com.example.store.core.model.product.Product


data class NetworkProduct(
    val description: String,
    val id: String,
    val image: String,
    val store: ProductStore,
    val title: String
)

fun NetworkProduct.asExternalModel(): Product =
    Product(
        id = id,
        description = description,
        image = image,
        storeId = store.id,
        storeName = store.name,
        title = title
    )
