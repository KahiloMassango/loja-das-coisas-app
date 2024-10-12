package com.example.store.data.repository

import com.example.store.core.data.repository.DefaultCartRepository
import com.example.store.core.model.Product
import com.example.store.data.testdoubles.FakeCartDao
import com.example.store.testdata.CART_PRODUCT_1
import com.example.store.testdata.CART_PRODUCT_2
import com.example.store.testdata.CART_PRODUCT_3
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultCartRepositoryTest {

    private val defaultDelta = 0.000000

    private lateinit var repository: DefaultCartRepository

    @Before
    fun setUp() {
        val cartDao = FakeCartDao()
        repository = DefaultCartRepository(cartDao)
    }

    @Test
    fun getCartProductsStream_WhenCalled_ReturnsEmptyListInitially() = runTest {
        assertEquals(emptyList<Product>(), repository.getCartProductsStream().first())
    }

    @Test
    fun getCartProductsStream_WhenProductAdded_ReturnsUpdatedProductList() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        assertEquals(listOf(CART_PRODUCT_1), repository.getCartProductsStream().first())

        repository.addToCart(CART_PRODUCT_2)
        assertEquals(listOf(CART_PRODUCT_1, CART_PRODUCT_2), repository.getCartProductsStream().first())
    }

    @Test
    fun getCartProductsStream_WhenProductRemoved_ReturnsUpdatedProductList() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        assertEquals(listOf(CART_PRODUCT_1), repository.getCartProductsStream().first())

        repository.removeCartProduct(CART_PRODUCT_1.id)
        assertEquals(emptyList<Product>(), repository.getCartProductsStream().first())
    }

    @Test
    fun getCartProductsCountStream_WhenCalled_ReturnsZeroInitially() = runTest {
        assertEquals(0, repository.getCartProductsCountStream().first())
    }

    @Test
    fun getCartProductsCountStream_WhenProductAdded_ReturnsUpdatedCount() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        repository.addToCart(CART_PRODUCT_3)

        assertEquals(2, repository.getCartProductsCountStream().first())
    }

    @Test
    fun getCartProductsCountStream_WhenProductRemoved_ReturnsUpdatedCount() = runTest {
        repository.addToCart(CART_PRODUCT_3)
        assertEquals(1, repository.getCartProductsCountStream().first())

        repository.removeCartProduct(CART_PRODUCT_3.id)
        assertEquals(0, repository.getCartProductsCountStream().first())
    }

    @Test
    fun getCartTotalStream_WhenCalled_ReturnsZeroInitially() = runTest {
        assertEquals(0, repository.getCartProductsCountStream().first())
    }

    @Test
    fun getCartTotalStream_WhenProductAdded_ReturnsUpdatedTotalPrice() = runTest {
        repository.addToCart(CART_PRODUCT_3)
        repository.addToCart(CART_PRODUCT_1)

        val totalPrice = listOf(CART_PRODUCT_3, CART_PRODUCT_1)
            .fold(0.0) { sum, p -> sum + (p.price * p.quantity) }

        assertEquals(totalPrice, repository.getCartTotalStream().first(), defaultDelta)
    }

    @Test
    fun addToCart_WhenProductAdded_ProductAppearsInCart() = runTest {
        repository.addToCart(CART_PRODUCT_1)

        assertEquals(listOf(CART_PRODUCT_1), repository.getCartProductsStream().first())
    }

    @Test
    fun removeCartProduct_WhenProductRemoved_ProductNoLongerInCart() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        repository.removeCartProduct(CART_PRODUCT_1.id)

        assertEquals(0, repository.getCartProductsStream().first().size)
    }

    @Test
    fun updateQuantity_WhenQuantityUpdated_ProductQuantityIsUpdated() = runTest {
        repository.addToCart(CART_PRODUCT_1)

        val expectedQuantity = 2
        repository.updateQuantity(CART_PRODUCT_1.id, expectedQuantity)

        val actualQuantity = repository.getCartProductsStream().first().first().quantity
        assertEquals(expectedQuantity, actualQuantity)
    }

    @Test
    fun addToCart_WhenDuplicateProductAdded_DoNotAddDuplicate() = runTest {
        repository.addToCart(CART_PRODUCT_1)
        repository.addToCart(CART_PRODUCT_1)
        assertEquals(listOf(CART_PRODUCT_1), repository.getCartProductsStream().first())
    }

    @Test
    fun addToCart_WhenDuplicateProductAddedWithDifferentVariation_AddProductInCart() = runTest {
        val variation1 = CART_PRODUCT_1.copy(color = "black", size = "M")
        val variation2 = CART_PRODUCT_1.copy(color = "black", size = "XL")

        repository.addToCart(variation1)
        repository.addToCart(variation2)

        assertEquals(listOf(variation1, variation2), repository.getCartProductsStream().first())
    }

    @Test
    fun getCartProductsCountStream_WhenCartIsEmpty_ReturnsZeroCount() = runTest {
        assertEquals(0, repository.getCartProductsCountStream().first())
    }

    @Test
    fun getCartTotalStream_WhenCartIsEmpty_ReturnsZeroTotal() = runTest {
        assertEquals(0, repository.getCartProductsCountStream().first())
    }
}