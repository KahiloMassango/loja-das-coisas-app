package com.example.store.core.network.model.order.response

import com.example.store.core.model.order.Order

data class OrderDtoRes(
    val id: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val latitude: Double,
    val longitude: Double
)

data class OrdersDtoRes(
    val delivered: List<OrderDtoRes>,
    val pending: List<OrderDtoRes>,
    val canceled: List<OrderDtoRes>
)

fun OrderDtoRes.asExternalModel() = Order(
    id = id,
    subTotal = subTotal,
    deliveryFee = deliveryFee,
    total = total,
    deliveryAddressName = deliveryAddressName,
    paymentType = paymentType,
    deliveryMethod = deliveryMethod,
)