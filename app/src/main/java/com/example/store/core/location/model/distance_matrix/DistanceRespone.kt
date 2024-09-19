package com.example.store.core.location.model.distance_matrix

data class DistanceRespone(
    val destination_addresses: List<String>,
    val origin_addresses: List<String>,
    val rows: List<Row>,
    val status: String
)