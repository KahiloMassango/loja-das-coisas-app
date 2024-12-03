package com.example.store.core.data.repository

import com.example.store.core.model.CartProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartProductsStream(): Flow<List<CartProduct>>
    fun getCartProductsCountStream(): Flow<Int>
    fun getCartTotalStream(): Flow<Double>
    suspend fun addToCart(product: CartProduct)
    suspend fun removeCartProduct(id: Int)
    suspend fun updateQuantity(id: Int, quantity: Int)


}