package com.example.store.core.model.resource

import com.example.store.core.model.cart.DeliveryMethod

fun calculateOrderTotal(
    deliveryFee: Int,
    cartTotal: Int,
    deliveryMethod: DeliveryMethod
): Int {
    return when (deliveryMethod) {
        DeliveryMethod.ENTREGA -> deliveryFee + cartTotal
        else -> cartTotal
    }
}