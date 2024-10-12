package com.example.store.data.model

import com.example.store.core.data.model.asFavoriteProductEntity
import com.example.store.testdata.PRODUCT_1
import org.junit.Test
import org.junit.Assert.*

class FavoriteProductEntityTest {

    @Test
    fun `product can be mapped to favorite product entity`() {
        val product = PRODUCT_1

        val entity = product.asFavoriteProductEntity()

        assertEquals(product.id, entity.id)
        assertEquals(product.name, entity.name)
        assertEquals(product.storeName, entity.storeName)
        assertEquals(product.price, entity.price, 0.0)
        assertEquals(product.images[0], entity.imageUrl)
        assertEquals(product.availableColors[0], entity.color)
        assertEquals(product.availableSizes[0], entity.size)
    }
}