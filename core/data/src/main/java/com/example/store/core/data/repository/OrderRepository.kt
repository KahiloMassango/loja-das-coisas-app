package com.example.store.core.data.repository

import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderStateSummary
import com.example.store.core.model.order.OrderWithItems

interface OrderRepository {

    suspend fun getOrderById(id: String): Result<OrderWithItems>

    suspend fun getMyOrders(): Result<OrderStateSummary>

    suspend fun placeOrder(
        total: Int,
        subTotal: Int,
        deliveryFee: Int,
        deliveryAddressName: String,
        latitude: Double,
        longitude: Double,
        paymentType: String,
        deliveryMethod: String,
        cartProductItems: List<CartProductItem>
    ): Result<Order>

}