package com.example.store.repositories

import com.example.store.core.data.repository.DefaultCartRepository
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
    lateinit var repository: DefaultCartRepository

    @Before
    fun setUp() {
        cartDao = FakeCartDao()
        repository = DefaultCartRepository(cartDao)
    }

    @Test
    fun `Return empty list when no products in cart`() = runTest {
        assertEquals(emptyList<Product>(), repository.getCartProductsStream().first())
    }

    @Test
    fun `Return count 0 when no products in cart`() = runTest {
        assertEquals(0, repository.getCartProductsCountStream().first())
    }

    @Test
    fun `Correct cart total`() = runTest {
        val expectedCartTotal = listOf(CART_PRODUCT_1, CART_PRODUCT_2)
            .fold(0.0) { sum, p -> sum + (p.price * p.quantity) }

        repository.addToCart(CART_PRODUCT_1)
        repository.addToCart(CART_PRODUCT_2)

        assertEquals(expectedCartTotal, repository.getCartTotalStream().first(), defaultDelta)
    }

    @Test
    fun `Incorrect cart total`() = runTest {

        val expectedCartTotal = listOf(CART_PRODUCT_1, CART_PRODUCT_2)
            .fold(0.0) { sum, p -> sum + (p.price * p.quantity) }

        repository.addToCart(CART_PRODUCT_1)
        repository.addToCart(CART_PRODUCT_2)
        repository.addToCart(CART_PRODUCT_3)

        assertNotEquals(expectedCartTotal, repository.getCartTotalStream().first(), defaultDelta)
    }

    @Test
    fun `add products correctly`() = runTest {
        repository.addToCart(CART_PRODUCT_3)
        repository.addToCart(CART_PRODUCT_2)

        assertEquals(listOf(CART_PRODUCT_3, CART_PRODUCT_2), repository.getCartProductsStream().first())
    }

    @Test
    fun `Duplicated product do not not add`() = runTest {

        repository.addToCart(CART_PRODUCT_3)
        repository.addToCart(CART_PRODUCT_3)
        val unexpectedList = listOf(CART_PRODUCT_3, CART_PRODUCT_3)

        assertNotEquals(unexpectedList, repository.getCartProductsStream().first())
    }

    @Test
    fun `given id remove product correctly`() = runTest {
        repository.addToCart(CART_PRODUCT_3)
        repository.removeCartProduct(CART_PRODUCT_3.id)

        assert(CART_PRODUCT_3 !in repository.getCartProductsStream().first())
    }

    @Test
    fun `update quantity correctly`() = runTest {
        val expectedQuantity = 5
        repository.addToCart(CART_PRODUCT_1)

        repository.updateQuantity(CART_PRODUCT_1.id, expectedQuantity)

        val actualQuantity = repository.getCartProductsStream().first().first().quantity

        assertEquals(expectedQuantity, actualQuantity)
    }

    @Test
    fun `add same product with variation`() = runTest {
        val productVariation1 = CART_PRODUCT_1.copy(color = "color1")
        val productVariation2 = CART_PRODUCT_1.copy(size = "size2")
        repository.addToCart(productVariation1)
        repository.addToCart(productVariation2)

       val expectedProducts = listOf(productVariation1, productVariation2)

        assertEquals(expectedProducts, repository.getCartProductsStream().first())
    }

    @Test
    fun `add same product with same variation`() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        repository.addToCart(CART_PRODUCT_1)

       val expectedProducts = listOf(CART_PRODUCT_1)

        assertEquals(expectedProducts, repository.getCartProductsStream().first())
    }




}