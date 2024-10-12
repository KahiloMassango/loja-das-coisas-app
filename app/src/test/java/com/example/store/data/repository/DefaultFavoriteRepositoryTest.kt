package com.example.store.data.repository

import com.example.store.core.data.repository.DefaultFavoriteRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.model.FavoriteProduct
import com.example.store.data.testdoubles.FakeFavoriteDao
import com.example.store.testdata.PRODUCT_1
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DefaultFavoriteRepositoryTest {
    
    private lateinit var repository: FavoriteRepository

    @Before
    fun setUp() {
        val favoritesDao = FakeFavoriteDao()
        repository = DefaultFavoriteRepository(favoritesDao)

    }

    @Test
    fun getFavoriteProductsStream_WhenCalled_ReturnsEmptyListInitially() = runTest {
        assertEquals(emptyList<FavoriteProduct>(), repository.getFavoriteProductsStream().first())
    }

    @Test
    fun addFavoriteProduct_WhenFavoriteAdded_ReturnsUpdatedList() = runTest {
        repository.addFavoriteProduct(PRODUCT_1)

        val entity = FavoriteProduct(
            id = PRODUCT_1.id,
            name = PRODUCT_1.name,
            price = PRODUCT_1.price,
            storeName = PRODUCT_1.storeName,
            imageUrl = PRODUCT_1.images[0],
            color = PRODUCT_1.availableColors[0],
            size = PRODUCT_1.availableSizes[0],
            avgRating = PRODUCT_1.averageRating,
            totalRatings = PRODUCT_1.totalRating
        )

        assertEquals(listOf(entity), repository.getFavoriteProductsStream().first())
    }

    @Test
    fun addFavoriteProduct_WhenDuplicatedFavoriteAdded_IgnoresDuplicate() = runTest {
        repository.addFavoriteProduct(PRODUCT_1)
        repository.addFavoriteProduct(PRODUCT_1)

        val entity = FavoriteProduct(
            id = PRODUCT_1.id,
            name = PRODUCT_1.name,
            price = PRODUCT_1.price,
            storeName = PRODUCT_1.storeName,
            imageUrl = PRODUCT_1.images[0],
            color = PRODUCT_1.availableColors[0],
            size = PRODUCT_1.availableSizes[0],
            avgRating = PRODUCT_1.averageRating,
            totalRatings = PRODUCT_1.totalRating
        )

        assertEquals(listOf(entity), repository.getFavoriteProductsStream().first())
    }

    @Test
    fun removeFavoriteProduct_WhenFavoriteRemoved_ReturnsUpdatedList() = runTest {
        repository.addFavoriteProduct(PRODUCT_1)

        assertTrue(repository.checkFavoriteProductStream(PRODUCT_1.id).first())

        repository.removeFavoriteProduct(PRODUCT_1.id)
        assertFalse(repository.checkFavoriteProductStream(PRODUCT_1.id).first())

    }




}