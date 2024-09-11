package com.example.store.core.location.model

import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.google.gson.annotations.SerializedName

data class GeocodeResponseItem(
    @SerializedName("display_name")
    val displayName: String,
    val lat: String,
    val lon: String,
)

fun GeocodeResponseItem.asExternalModel(): Location =
    Location(
        name = this.displayName,
        coordinates = LocationCoordinates(this.lat.toDouble(), this.lon.toDouble())
    )