package com.example.store.presentation.screens.my_orders.model

enum class OrderTabs(
    val tabTitle: String
) {
    DELIVERED("Entregue"),
    PROCESSING( "Processando"),
    CANCELED( "Cancelado")
}
