package com.example.store.core.data.repository

import com.example.store.core.model.FavoriteProduct
import com.example.store.core.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>>

    fun checkFavoriteProductFlow(productId: String): Flow<Boolean>

    suspend fun addFavoriteProduct(product: Product)

    suspend fun removeFavoriteProduct(productId: String)

}