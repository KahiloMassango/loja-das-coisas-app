package com.example.store.core.data.repository

import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.model.CartProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartProducts(): Flow<List<CartProduct>>
    fun getCartProductsCount(): Flow<Int>
    fun getCartTotal(): Flow<Double>
    suspend fun addCartProduct(product: CartProductEntity)
    suspend fun removeCartProduct(id: Int)
    suspend fun updateProductQuantity(id: Int, quantity: Int)


}