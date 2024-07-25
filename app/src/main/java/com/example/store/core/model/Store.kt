package com.example.store.core.model

data class Store(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val offersDelivery: Boolean,
    val sellerId: String,
)
