package com.example.store.core.model

data class Order(
    val id: Int = 0,
    val cartTotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val orderTotal: Double = 0.0,
    val deliveryLocationName: String = "DEFAULT_LOCATION_NAME",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val paymentType: String = "Cash",
    val deliveryMethod: DeliveryMethod = DeliveryMethod.DELIVERY
)

