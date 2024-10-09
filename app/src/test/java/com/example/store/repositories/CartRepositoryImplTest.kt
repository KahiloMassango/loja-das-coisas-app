package com.example.store.repositories

import com.example.store.core.data.repository.CartRepositoryImpl
import com.example.store.core.database.dao.CartDao
import com.example.store.core.model.Product
import com.example.store.data.CART_PRODUCT_1
import com.example.store.data.CART_PRODUCT_2
import com.example.store.data.CART_PRODUCT_3
import com.example.store.repositories.datasources.FakeCartDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CartRepositoryImplTest {

    val defaultDelta = 0.000000

    lateinit var cartDao: CartDao
    lateinit var repository: CartRepositoryImpl

    @Before
    fun setUp() {
        cartDao = FakeCartDao()
        repository = CartRepositoryImpl(cartDao)
    }

    @Test
    fun `Return empty list when no products in cart`() = runTest {
        assertEquals(emptyList<Product>(), repository.getCartProducts().first())
    }

    @Test
    fun `Return count 0 when no products in cart`() = runTest {
        assertEquals(0, repository.getCartProductsCount().first())
    }

    @Test
    fun `Correct cart total`() = runTest {
        val expectedCartTotal = listOf(CART_PRODUCT_1, CART_PRODUCT_2)
            .fold(0.0) { sum, p -> sum + (p.price * p.quantity) }

        repository.addCartProduct(CART_PRODUCT_1)
        repository.addCartProduct(CART_PRODUCT_2)

        assertEquals(expectedCartTotal, repository.getCartTotal().first(), defaultDelta)
    }

    @Test
    fun `Incorrect cart total`() = runTest {

        val expectedCartTotal = listOf(CART_PRODUCT_1, CART_PRODUCT_2)
            .fold(0.0) { sum, p -> sum + (p.price * p.quantity) }

        repository.addCartProduct(CART_PRODUCT_1)
        repository.addCartProduct(CART_PRODUCT_2)
        repository.addCartProduct(CART_PRODUCT_3)

        assertNotEquals(expectedCartTotal, repository.getCartTotal().first(), defaultDelta)
    }

    @Test
    fun `add products correctly`() = runTest {
        repository.addCartProduct(CART_PRODUCT_3)
        repository.addCartProduct(CART_PRODUCT_2)

        assertEquals(listOf(CART_PRODUCT_3, CART_PRODUCT_2), repository.getCartProducts().first())
    }

    @Test
    fun `Duplicated product do not not add`() = runTest {

        repository.addCartProduct(CART_PRODUCT_3)
        repository.addCartProduct(CART_PRODUCT_3)
        val unexpectedList = listOf(CART_PRODUCT_3, CART_PRODUCT_3)

        assertNotEquals(unexpectedList, repository.getCartProducts().first())
    }

    @Test
    fun `given id remove product correctly`() = runTest {
        repository.addCartProduct(CART_PRODUCT_3)
        repository.removeCartProduct(CART_PRODUCT_3.id)

        assert(CART_PRODUCT_3 !in repository.getCartProducts().first())
    }

    @Test
    fun `update quantity correctly`() = runTest {
        val expectedQuantity = 5
        repository.addCartProduct(CART_PRODUCT_1)

        repository.updateProductQuantity(CART_PRODUCT_1.id, expectedQuantity)

        val actualQuantity = repository.getCartProducts().first().first().quantity

        assertEquals(expectedQuantity, actualQuantity)
    }

    @Test
    fun `add same product with variation`() = runTest {
        val productVariation1 = CART_PRODUCT_1.copy(color = "color")
        val productVariation2 = CART_PRODUCT_1.copy(size = "size")
        repository.addCartProduct(CART_PRODUCT_1)
        repository.addCartProduct(productVariation1)
        repository.addCartProduct(productVariation2)

       val expectedProducts = listOf(CART_PRODUCT_1, productVariation1, productVariation2)

        assertEquals(expectedProducts, repository.getCartProducts().first())
    }




}