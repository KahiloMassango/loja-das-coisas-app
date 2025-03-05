package com.example.store.core.model.order

data class Order(
    val id: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String
)

data class OrderStateSummary(
    val delivered: List<Order>,
    val pending: List<Order>,
    val canceled: List<Order>
)

data class OrderWithItems(
    val id: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val orderItems: List<OrderItem>
)


