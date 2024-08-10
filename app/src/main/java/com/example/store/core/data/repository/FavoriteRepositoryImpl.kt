package com.example.store.core.data.repository

import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val favoritesDao: FavoritesDao
): FavoriteRepository {

    override fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>> {
       return favoritesDao.getFavoriteProducts().map { list -> list.map { it.asExternalModel() } }
    }

    override fun checkProductIsFavorite(productId: String): Flow<Boolean> {
        return favoritesDao.getProductCount(productId).map { it == 1 }

    }

    override suspend fun insertFavoriteProduct(product: FavoriteProductEntity) {
        favoritesDao.insertFavoriteProduct(product)
    }

    override suspend fun deleteFavoriteProduct(productId: String) {
        favoritesDao.deleteFavoriteProduct(productId)
    }
}