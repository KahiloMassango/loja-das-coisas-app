package com.example.store.core.model

import com.example.store.core.model.cart.DeliveryMethod

data class Order(
    val id: Int = 0,
    val cartTotal: Int = 0,
    val deliveryFee: Int = 0,
    val orderTotal: Int = 0,
    val deliveryLocationName: String = "DEFAULT_LOCATION_NAME",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val paymentType: String = "Cash",
    val deliveryMethod: DeliveryMethod = DeliveryMethod.DELIVERY
)

