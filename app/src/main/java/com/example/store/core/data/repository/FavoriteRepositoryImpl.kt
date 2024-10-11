package com.example.store.core.data.repository

import com.example.store.core.data.model.asFavoriteProductEntity
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.FavoriteProduct
import com.example.store.core.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val favoritesDao: FavoritesDao
): FavoriteRepository {

    override fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>> {
       return favoritesDao.getFavoriteProducts().map { list -> list.map { it.asExternalModel() } }
    }

    override fun checkFavoriteProductStream(productId: String): Flow<Boolean> {
        return favoritesDao.getProductCount(productId).map { it == 1 }

    }

    override suspend fun addFavoriteProduct(product: Product) {
        favoritesDao.insertFavoriteProduct(product.asFavoriteProductEntity())
    }

    override suspend fun removeFavoriteProduct(productId: String) {
        favoritesDao.deleteFavoriteProduct(productId)
    }
}