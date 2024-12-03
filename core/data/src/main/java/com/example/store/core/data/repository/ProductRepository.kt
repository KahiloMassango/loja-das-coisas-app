package com.example.store.core.data.repository

import com.example.store.core.model.product.Product
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo
import com.example.store.core.model.product.ProductWithVariation

interface ProductRepository {
    suspend fun getProducts(category: String, subCategory: String): List<Product>
    suspend fun getAllProducts():  List<Product>
    suspend fun getProductById(id: Int): ProductWithVariation
    /*suspend fun getNewProducts(category: String): List<Product>
    suspend fun getProductRatingInfo(productId: String): RatingInfo
    suspend fun getProductRatings(productId: String): List<Rating>*/
}