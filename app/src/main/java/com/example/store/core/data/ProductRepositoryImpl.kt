package com.example.store.core.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.store.core.data.mock.productList
import com.example.store.core.data.mock.ratingInfosList
import com.example.store.core.data.mock.ratingsList
import com.example.store.core.data.repository.repository.ProductRepository
import com.example.store.core.model.Product
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo

class ProductRepositoryImpl: ProductRepository {

    private val products = productList
    private val ratings = ratingsList
    private val ratingInfos = ratingInfosList

    override fun getProducts(gender: String, category: String): List<Product> {
        return products.filter { it.section == gender && it.category == category }
    }

    override fun getProductById(id: String): Product {
        return products.find { it.id == id }!!
    }

    override fun getProductsByCategory(category: String): List<Product> {
        return products.filter { it.category == category }
    }

    override fun getNewProducts(category: String): List<Product> {
        return products.filter { it.category == category }.sortedByDescending { it.publishedAt }
    }

    override fun getProductRatingInfo(productId: String): RatingInfo {
        return ratingInfos.find { it.productId == productId }!!
    }

    override fun getProductRatings(productId: String): List<Rating> {
        return ratings.filter { it.productId == productId }
    }
}