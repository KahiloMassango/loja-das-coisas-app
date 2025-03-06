package com.example.store.core.network.model.order.response

import com.example.store.core.model.order.OrderWithItems


data class OrderWithItemsDtoRes(
    val id: String,
    val storeName: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val date: String,
    val status: String,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val orderItems: List<OrderItemDtoRes>
)

fun OrderWithItemsDtoRes.asExternalModel() = OrderWithItems(
    id = id,
    storeName = storeName,
    status = status,
    subTotal = subTotal,
    deliveryFee = deliveryFee,
    date = date,
    total = total,
    deliveryAddress = deliveryAddressName,
    paymentType = paymentType,
    deliveryMethod = deliveryMethod,
    orderItems = orderItems.map { it.asExternalModel() }
)


