package com.example.store.core.data.repository

import com.example.store.core.model.cart.CartProduct
import com.example.store.core.model.product.ProductItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartProductsStream(): Flow<List<CartProduct>>
    fun getCartProductsCountStream(): Flow<Int>
    fun getCartTotalStream(): Flow<Int>
    suspend fun addToCart(productName: String, imageUrl: String, productItem: ProductItem)
    suspend fun removeCartProduct(id: String)
    suspend fun updateQuantity(id: String, quantity: Int)


}