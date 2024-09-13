package com.example.store.core.model

data class Order(
    val id: Int,
    val date: String,
    val deliveryMethod: DeliveryMethod,
    val deliveryAddress: Location,
    val paymentType: String,
    val cartTotal: Double,
    val deliveryPrice: Double,
    val orderTotal: Double,
)