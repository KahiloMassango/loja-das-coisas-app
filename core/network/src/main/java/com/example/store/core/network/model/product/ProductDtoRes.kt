package com.example.store.core.network.model.product

import com.example.store.core.model.product.Category
import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation


data class ProductDtoRes(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val storeId: String,
    val storeName: String,
    val minPrice: Int
)

fun ProductDtoRes.asExternalModel(): Product =
    Product(
        id = id,
        description = description,
        image = imageUrl,
        storeId = storeId,
        storeName = storeName,
        name = name,
        minPrice = minPrice
    )

data class ProductWithVariationDtoRes(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val productItems: List<ProductItemDtoRes>,
    val storeId: String,
    val storeName: String,
    val category: CategoryDtoRes,
)

fun ProductWithVariationDtoRes.asExternalModel() = ProductWithVariation(
    id = id,
    description = description,
    image = imageUrl,
    storeId = storeId,
    storeName = storeName,
    name = name,
    productItems = productItems.map { it.asExternalModel() },
    category = category.asExternalModel()
)

data class CategoryDtoRes(
    val name: String,
    val hasColorVariation: Boolean,
    val hasSizeVariation: Boolean,
)

fun CategoryDtoRes.asExternalModel() = Category(
    name = name,
    hasColorVariation = hasColorVariation,
    hasSizeVariation = hasSizeVariation
)


