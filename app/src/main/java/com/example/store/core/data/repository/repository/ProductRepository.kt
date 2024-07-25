package com.example.store.core.data.repository.repository

import com.example.store.core.model.Product
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo

interface ProductRepository {
    fun getProducts(gender: String, category: String): List<Product>
    fun getProductById(id: String): Product
    fun getProductsByCategory(category: String): List<Product>
    fun getNewProducts(category: String): List<Product>
    fun getProductRatingInfo(productId: String): RatingInfo
    fun getProductRatings(productId: String): List<Rating>
}