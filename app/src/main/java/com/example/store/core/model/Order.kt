package com.example.store.core.model

import com.example.store.core.database.model.DEFAULT_LOCATION_LATITUDE
import com.example.store.core.database.model.DEFAULT_LOCATION_LONGITUDE
import com.example.store.core.database.model.DEFAULT_LOCATION_NAME
import com.example.store.core.database.model.OrderEntity

data class Order(
    val id: Int = 0,
    val cartTotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val orderTotal: Double = 0.0,
    val deliveryLocationName: String = DEFAULT_LOCATION_NAME,
    val latitude: Double = DEFAULT_LOCATION_LATITUDE,
    val longitude: Double = DEFAULT_LOCATION_LONGITUDE,
    val paymentType: String = "Cash",
    val deliveryMethod: DeliveryMethod = DeliveryMethod.DELIVERY
)

fun Order.asEntity(): OrderEntity = OrderEntity(
    id = id,
    cartTotal = cartTotal,
    deliveryFee = deliveryFee,
    orderTotal = orderTotal,
    deliveryLocationName = deliveryLocationName,
    latitude = latitude,
    longitude = longitude,
    paymentType = paymentType,
    deliveryMethod = deliveryMethod
)