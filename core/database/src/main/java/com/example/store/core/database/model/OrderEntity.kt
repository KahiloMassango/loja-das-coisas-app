package com.example.store.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.cart.DeliveryMethod
import com.example.store.core.model.Order

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val cartTotal: Int,
    val deliveryFee: Int,
    val orderTotal: Int,
    val deliveryLocationName: String,
    val latitude: Double,
    val longitude: Double,
    val paymentType: String,
    val deliveryMethod: DeliveryMethod,
)

fun OrderEntity.asExternalModel(): Order = Order(
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
