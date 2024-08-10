package com.example.store.core.data.repository

import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.CartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val cartDao: CartDao
): CartRepository {

    override fun getCartProducts(): Flow<List<CartProduct>> {
        return cartDao.getCartProducts().map { list -> list.map { it.asExternalModel() } }
    }

    override fun getCartProductsCount(): Flow<Int> {
        return cartDao.getCartProductCount()
    }

    override fun getCartTotal(): Flow<Double> {
        return cartDao.getCartTotal()
    }

    override suspend fun addCartProduct(product: CartProductEntity) {
        val checkProduct = cartDao.getProductByUUID(product.uuid)
        if(checkProduct != null){
            if(checkProduct.color != product.color || checkProduct.size != product.size){
                cartDao.insertCartProduct(product)
            }
        } else {
            cartDao.insertCartProduct(product)
        }

    }

    override suspend fun removeCartProduct(id: Int) {
        cartDao.deleteCartProduct(id)
    }

    override suspend fun updateProductQuantity(id: Int, quantity: Int) {
        cartDao.updateQuantity(id, quantity)
    }
}