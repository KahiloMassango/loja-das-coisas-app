package com.example.store.core.data.repository

import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>>

    fun checkProductIsFavorite(productId: String): Flow<Boolean>

    suspend fun insertFavoriteProduct(product: FavoriteProductEntity)

    suspend fun deleteFavoriteProduct(productId: String)

}