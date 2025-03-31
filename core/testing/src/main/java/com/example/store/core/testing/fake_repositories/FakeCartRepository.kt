package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.CartRepository
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.product.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCartRepository: CartRepository {

    private val cartProducts = mutableListOf<CartProductItem>()

    override fun getCartProductsStream(): Flow<List<CartProductItem>> {
        return flowOf(cartProducts)
    }

    override fun getCartProducts(): List<CartProductItem> {
        return cartProducts
    }

    override fun getCartProductsCountStream(): Flow<Int> {
        return flowOf(cartProducts.size)
    }

    override fun getCartTotalStream(): Flow<Int> {
        return flowOf(cartProducts.sumOf { it.price * it.quantity })
    }

    override suspend fun removeCartProduct(id: String) {
        cartProducts.removeIf { it.id == id }
    }

    override suspend fun updateQuantity(id: String, quantity: Int) {
        val index = cartProducts.indexOfFirst { it.id == id }
        if (index != -1) {
            cartProducts[index] = cartProducts[index].copy(quantity = quantity)
        }
    }

    override suspend fun clearCart() {
        cartProducts.clear()
    }

    override suspend fun addToCart(
        productName: String,
        productId: String,
        imageUrl: String,
        productItem: ProductItem
    ) {
        val cartItem = CartProductItem(
            id = productItem.id,
            name = productName,
            imageUrl = imageUrl,
            price = productItem.price,
            quantity = 1,
            productId = productId,
            color = productItem.color,
            size = productItem.size,
            stockQuantity = productItem.stockQuantity,
        )
        cartProducts.add(cartItem)
    }
}