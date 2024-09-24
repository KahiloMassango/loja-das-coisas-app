package com.example.store.core.data.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.example.store.core.location.DistanceMatrixApiService
import com.example.store.core.location.GeocodeApiService
import com.example.store.core.location.model.asExternalModel
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
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
    override suspend fun getCurrentLocation(): LocationCoordinates? {
        val location = locationService.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, null
        )
            .await()

        if (location == null) {
            return null
        }
        return LocationCoordinates(location.latitude, location.longitude)

    }

    @Suppress("DEPRECATION")
    override suspend fun getLocationName(latitude: Double, longitude: Double): String {
        return try {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    suspendCoroutine { continuation ->
                        geocodeService.getFromLocation(latitude, longitude, 1) { result ->
                            continuation.resume(result.firstOrNull()?.getAddressLine(0) ?: "")
                        }
                    }
                }

                else -> {
                    geocodeService.getFromLocation(latitude, longitude, 1)
                        ?.firstOrNull()?.getAddressLine(0) ?: ""
                }
            }

        } catch (e: Exception) {
            Log.d("LocationRepository", "getLocationName: $e")
            " "
        }
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return locationApisService.getLocationsByName(query)
            .map { it.asExternalModel() }
    }

    override suspend fun getLocationDistance(
        origin: LocationCoordinates,
        destination: LocationCoordinates
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