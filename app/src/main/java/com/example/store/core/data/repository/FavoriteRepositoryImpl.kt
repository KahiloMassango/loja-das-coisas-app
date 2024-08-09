package com.example.store.core.data.repository

import com.example.store.core.database.dao.FavoriteProductsDao
import com.example.store.core.database.model.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val favoriteProductsDao: FavoriteProductsDao
): FavoriteRepository {

    override fun getFavoriteProductsStream(): Flow<List<FavoriteProductEntity>> {
       return favoriteProductsDao.getFavoriteProducts()
    }

    override fun checkProductIsFavorite(productId: String): Flow<Boolean> {
        return favoriteProductsDao.getProductCount(productId).map { it == 1 }

    }

    override suspend fun insertFavoriteProduct(product: FavoriteProductEntity) {
        favoriteProductsDao.insertFavoriteProduct(product)
    }

    override suspend fun deleteFavoriteProduct(productId: String) {
        favoriteProductsDao.deleteFavoriteProduct(productId)
    }
}