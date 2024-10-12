package com.example.store.data.testdoubles

import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.model.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFavoriteDao: FavoritesDao {

    private val favoriteProducts = mutableListOf<FavoriteProductEntity>()

    override fun getFavoriteProducts(): Flow<List<FavoriteProductEntity>> {
        return flow { emit(favoriteProducts.toList()) }
    }

    override fun getProductCount(productId: String): Flow<Int> {
        return flow { emit(favoriteProducts.size) }
    }

    override suspend fun insertFavoriteProduct(product: FavoriteProductEntity) {
        favoriteProducts.add(product)
    }

    override suspend fun deleteFavoriteProduct(productId: String) {
        favoriteProducts.removeIf { it.id == productId }

    }
}