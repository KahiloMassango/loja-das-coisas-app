package com.example.store.data.testdoubles

import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.model.CartProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCartDao : CartDao {
    private val cart = mutableListOf<CartProductEntity>()

    override fun insertCartProduct(cartProductEntity: CartProductEntity) {
        cart.add(cartProductEntity)
    }

    override suspend fun deleteCartProduct(id: Int) {
        cart.removeAll { it.id == id }
    }

    override suspend fun updateQuantity(id: Int, quantity: Int) {
        val product = cart.find { it.id == id }
        if(cart.removeAll { it.id == id }){
            cart.add(product!!.copy(quantity = quantity))
        }
    }

    override suspend fun getProductByUUID(uuid: String): CartProductEntity? {
        return cart.find { it.uuid == uuid }
    }

    override fun getCartTotalStream(): Flow<Double> {
        val cartTotal = cart.fold(0.0) { sum, p -> sum + (p.price * p.quantity) }
        return flow {
            emit(cartTotal)
        }
    }


    override fun getCartProductCount(): Flow<Int> {
        return flow { emit(cart.size) }
    }

    override fun getCartProducts(): Flow<List<CartProductEntity>> {
        return flow { emit(cart.toList()) }
    }
}