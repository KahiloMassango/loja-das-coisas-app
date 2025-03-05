package com.example.store.core.network.model.order.response

import com.example.store.core.model.order.OrderWithItems


data class OrderWithItemsDtoRes(
    val id: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val orderItems: List<OrderItemDtoRes>
)

fun OrderWithItemsDtoRes.asExternalModel() = OrderWithItems(
    id = id,
    subTotal = subTotal,
    deliveryFee = deliveryFee,
    total = total,
    deliveryAddressName = deliveryAddressName,
    paymentType = paymentType,
    deliveryMethod = deliveryMethod,
    orderItems = orderItems.map { it.asExternalModel() }
)


