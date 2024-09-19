package com.example.store.core.data.repository

import com.example.store.core.database.dao.OrderDao
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.DeliveryMethod
import com.example.store.core.model.Order
import com.example.store.core.model.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val orderDao: OrderDao
): OrderRepository {
    override fun addOrder(order: Order) {
        orderDao.addOrder(order.asEntity())
    }

    override suspend fun getOrder(): Order {
        return orderDao.getOrder().asExternalModel()
    }

    override fun getOrderStream(): Flow<Order> {
        return orderDao.getOrderStream().map { it.asExternalModel() }
    }

    override suspend fun resetOrder() {
        orderDao.resetOrder()
    }

    override suspend fun changeDeliveryLocation(
        locationName: String,
        latitude: Double,
        longitude: Double
    ) {
        orderDao.changeDeliveryLocation(locationName, latitude, longitude)
    }

    override suspend fun updatePaymentType(paymentType: String) {
        orderDao.updatePaymentType(paymentType)
    }

    override suspend fun updateDeliveryMethod(deliveryMethod: DeliveryMethod) {
        orderDao.updateDeliveryMethod(deliveryMethod)
    }

    override suspend fun updateDeliveryFee(deliveryFee: Double) {
        orderDao.updateDeliveryFee(deliveryFee)
    }

    override suspend fun updateCartTotal(cartTotal: Double) {
        orderDao.updateCartTotal(cartTotal)
    }

    override suspend fun updateOrderTotal(checkoutTotal: Double) {
        orderDao.updateOrderTotal(checkoutTotal)
    }
}