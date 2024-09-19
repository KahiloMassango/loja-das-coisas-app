package com.example.store.core.data.repository

import com.example.store.core.model.DeliveryMethod
import com.example.store.core.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun addOrder(order: Order)
    fun getOrderStream(): Flow<Order>
    suspend fun getOrder(): Order
    suspend fun resetOrder()
    suspend fun changeDeliveryLocation(locationName: String, latitude: Double, longitude: Double)
    suspend fun updatePaymentType(paymentType: String)
    suspend fun updateDeliveryMethod(deliveryMethod: DeliveryMethod)
    suspend fun updateDeliveryFee(deliveryFee: Double)
    suspend fun updateCartTotal(cartTotal: Double)
    suspend fun updateOrderTotal(checkoutTotal: Double)
}