package com.example.store.core.model.resource

import com.example.store.core.model.DeliveryMethod

fun calculateOrderTotal(
    deliveryPrice: Double,
    cartTotal: Double,
    deliveryMethod: DeliveryMethod
): Double {
    return when (deliveryMethod) {
        DeliveryMethod.DELIVERY -> deliveryPrice + cartTotal
        else -> cartTotal
    }
}