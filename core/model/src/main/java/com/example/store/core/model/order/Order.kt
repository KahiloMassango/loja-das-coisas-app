package com.example.store.core.model.order


data class Order(
    val id: String,
    val storeName: String,
    val date: String,
    val total: Int,
    val totalItems: Int
)

data class OrderStateSummary(
    val delivered: List<Order>,
    val pending: List<Order>,
    val canceled: List<Order>
)

data class OrderWithItems(
    val id: String,
    val storeName: String,
    val status: String,
    val date: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddress: String,
    val paymentType: String,
    val orderItems: List<OrderItem>
)


