package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProduct2
import com.example.store.core.testing.fake_data.product.fakeProduct3
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation1
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation2
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation3

class FakeProductRepository: ProductRepository {

    private var shouldFail = false

    private val fakeProducts = listOf(fakeProduct1, fakeProduct2, fakeProduct3)
    private val fakeProductsWithVariations = listOf(fakeProductWithVariation1, fakeProductWithVariation2, fakeProductWithVariation3)


    override suspend fun getProducts(gender: String?, category: String?): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            Result.success(fakeProducts)
        }
    }

    override suspend fun getProductById(id: String): Result<ProductWithVariation> {
        return if (shouldFail) {
            Result.failure(Exception("Server unavailable"))
        } else {
            val product = fakeProductsWithVariations.find { it.id == id }
            if (product != null) {
                Result.success(product)
            } else {
                Result.failure(Exception("Product not found"))
            }
        }
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Search service unavailable"))
        } else {
            val products = fakeProducts.filter { it.name.contains(query, ignoreCase = true) }
            Result.success(products)
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Category service error"))
        } else {
            val products = fakeProducts.filter { it.name.contains(category, ignoreCase = true) }
            Result.success(products)
        }
    }
}