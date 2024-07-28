package com.example.store.feature.my_orders.model

enum class OrderTabs(
    val tabTitle: String
) {
    DELIVERED("Entregue"),
    PROCESSING( "Processando"),
    CANCELED( "Cancelado")
}
