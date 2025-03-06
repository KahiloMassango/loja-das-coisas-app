package com.example.store.core.network.model.order.response

import com.example.store.core.model.order.Order

data class OrderDtoRes(
    val id: String,
    val storeName: String,
    val date: String,
    val total: Int,
    val orderTotalItems: Int,
)

data class OrdersDtoRes(
    val delivered: List<OrderDtoRes>,
    val pending: List<OrderDtoRes>,
    val canceled: List<OrderDtoRes>
)

fun OrderDtoRes.asExternalModel() = Order(
    id = id,
    total = total,
    date = date,
    storeName = storeName,
    totalItems = orderTotalItems
)