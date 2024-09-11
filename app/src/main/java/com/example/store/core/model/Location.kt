package com.example.store.core.model

data class Location(
    val name: String,
    val coordinates: LocationCoordinates
)

data class LocationCoordinates(
    val latitude: Double,
    val longitude: Double
)

