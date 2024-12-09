package com.example.store.core.model.store

data class Store(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val offersDelivery: Boolean,
    val sellerId: String,
)
