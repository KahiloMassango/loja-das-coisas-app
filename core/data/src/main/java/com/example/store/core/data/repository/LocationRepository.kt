package com.example.store.core.data.repository

import com.example.store.core.model.AddressLine
import com.example.store.core.model.Location
import com.google.android.gms.maps.model.LatLng

interface LocationRepository {

    suspend fun getLocationName(coordinates: LatLng): AddressLine

    suspend fun searchLocation(query: String): List<Location>

    suspend fun getLocationDistance(
        origin: LatLng,
        destination: LatLng
    ): Double?
}