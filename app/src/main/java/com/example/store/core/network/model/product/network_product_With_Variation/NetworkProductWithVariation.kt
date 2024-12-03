package com.example.store.core.network.model.product.network_product_With_Variation

import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.network.model.product.network_product.ProductStore


data class NetworkProductWithVariation(
    val description: String,
    val image: String,
    val productId: String,
    val reviews: List<Review>,
    val store: ProductStore,
    val title: String,
    val subCategory: String,
    val productItems: List<NetworkProductItem>
)

fun NetworkProductWithVariation.asExternalModel(): ProductWithVariation =
    ProductWithVariation(
        id = productId,
        description = description,
        image = image,
        storeId = store.id,
        storeName = store.name,
        title = title,
        subCategory = subCategory,
        productItems = productItems.map { it.asExternalModel() }
    )