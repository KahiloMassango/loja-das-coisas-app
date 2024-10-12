package com.example.store.data.model

import com.example.store.core.data.model.asEntity
import com.example.store.testdata.CART_PRODUCT_1
import org.junit.Assert.assertEquals
import org.junit.Test

class CartProductEntityTest {

    @Test
    fun `product can be mapped to cart product entity`() {
        val product = CART_PRODUCT_1

        val entity = product.asEntity()

        assertEquals(product.id, entity.id)
        assertEquals(product.uuid, entity.uuid)
        assertEquals(product.name, entity.name)
        assertEquals(product.price, entity.price, 0.0)
        assertEquals(product.imageUrl, entity.imageUrl)
        assertEquals(product.color, entity.color)
        assertEquals(product.size, entity.size)
        assertEquals(1, entity.quantity)
    }

}