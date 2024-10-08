package com.example.store.core.data.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.example.store.core.location.DistanceMatrixApiService
import com.example.store.core.location.GeocodeApiService
import com.example.store.core.location.model.asExternalModel
import com.example.store.core.model.AddressLine
import com.example.store.core.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationRepositoryImpl(
    private val locationApisService: GeocodeApiService,
    private val geocodeService: Geocoder,
    private val locationService: FusedLocationProviderClient,
    private val distanceMatrixService: DistanceMatrixApiService
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LatLng? {
        val location = locationService.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, null
        ).await()

        if (location == null) {
            return null
        }

        return LatLng(location.latitude, location.longitude)

    }

    @Suppress("DEPRECATION")
    override suspend fun getLocationName(coordinates: LatLng): AddressLine {
        return try {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    suspendCoroutine { continuation ->
                        geocodeService
                            .getFromLocation(
                                coordinates.latitude,
                                coordinates.longitude,
                                1
                            ) { result ->
                                val response = result.firstOrNull()
                                val addressLine = AddressLine(
                                    shortName = "${response?.locality}, ${response?.adminArea}",
                                    address = response?.getAddressLine(0) ?: "Sem nome",
                                )
                                continuation.resume(addressLine)
                            }
                    }
                }

                else -> {
                    val response = geocodeService.getFromLocation(
                        coordinates.latitude,
                        coordinates.longitude,
                        1
                    )?.firstOrNull()
                    AddressLine(
                        shortName = "${response?.locality}, ${response?.adminArea}",
                        address = response?.getAddressLine(0) ?: "Sem nome",
                    )
                }
            }

        } catch (e: Exception) {
            Log.d("LocationRepository", "getLocationName: $e")
            AddressLine(
                shortName = "Sem nome",
                address = "Endereço sem nome",
            )
        }
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return locationApisService.getLocationsByName(query)
            .map { it.asExternalModel() }
    }

    override suspend fun getLocationDistance(
        origin: LatLng,
        destination: LatLng
    ): Double? {
        val storeLocation = "${origin.latitude},${origin.longitude}"
        val userLocation = "${destination.latitude},${destination.longitude}"
        try {
            val distance = distanceMatrixService.getDistanceMatrix(storeLocation, userLocation)
            Log.d("repository", "getLocationDistance: $distance")
            return distance.rows[0].elements[0].distance?.text?.trim()?.removeSuffix("km")
                ?.toDouble()
        } catch (e: Exception) {
            Log.d("repository", "getLocationDistance: ${e.message}")
            return 0.0
        }

    }
}