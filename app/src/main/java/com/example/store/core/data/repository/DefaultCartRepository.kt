package com.example.store.core.data.repository

import com.example.store.core.data.model.asEntity
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.CartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultCartRepository(
    private val cartDao: CartDao
): CartRepository {

    override fun getCartProductsStream(): Flow<List<CartProduct>> {
        return cartDao.getCartProducts().map { list -> list.map { it.asExternalModel() } }
    }

    override fun getCartProductsCountStream(): Flow<Int> {
        return cartDao.getCartProductCount()
    }

    override fun getCartTotalStream(): Flow<Double> {
        return cartDao.getCartTotal()
    }

    override suspend fun addToCart(product: CartProduct) {
        // if product already exists in cart with the same properties do not add
        val checkProduct = cartDao.getProductByUUID(product.uuid)
        if(checkProduct != null){
            if(checkProduct.color != product.color || checkProduct.size != product.size){
                cartDao.insertCartProduct(product.asEntity())
            }
        } else {
            cartDao.insertCartProduct(product.asEntity())
        }

    }

    override suspend fun removeCartProduct(id: Int) {
        cartDao.deleteCartProduct(id)
    }

    override suspend fun updateQuantity(id: Int, quantity: Int) {
        cartDao.updateQuantity(id, quantity)
    }
}