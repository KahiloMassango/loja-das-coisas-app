package com.example.store.core.model.resource

import com.example.store.core.model.DeliveryMethod

fun calculateOrderTotal(
    deliveryFee: Double,
    cartTotal: Double,
    deliveryMethod: DeliveryMethod
): Double {
    return when (deliveryMethod) {
        DeliveryMethod.DELIVERY -> deliveryFee + cartTotal
        else -> cartTotal
    }
}