package com.example.store.core.data.repository

import com.example.store.core.data.P1
import com.example.store.core.data.P1V
import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.network.model.product.network_product.asExternalModel
import com.example.store.core.network.model.product.network_product_With_Variation.asExternalModel
import com.example.store.core.network.retrofit.AppApiService

class DefaultProductRepository(
    private val appApiService: AppApiService
): ProductRepository {
    val products = mutableListOf<Product>(P1, P1, P1, P1, P1, P1)

    override suspend fun getAllProducts(): List<Product> {
        return products
    }

    override suspend fun getProducts(category: String, subCategory: String): List<Product> {
        return products
    }

    override suspend fun getProductById(id: Int): ProductWithVariation {
        return P1V
    }
}