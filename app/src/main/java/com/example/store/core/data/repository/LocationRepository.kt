package com.example.store.core.data.repository

import android.location.Address
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getCurrentLocation(): LocationCoordinates?

    suspend fun getLocationName(latitude: Double, longitude: Double): String

    suspend fun searchLocation(query: String): List<Location>

    suspend fun getLocationDistance(
        origin: LocationCoordinates,
        destination: LocationCoordinates
    ): Double?
}