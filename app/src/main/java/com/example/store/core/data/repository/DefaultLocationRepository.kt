package com.example.store.core.data.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.store.core.location.GeocodeApiService
import com.example.store.core.location.model.asExternalModel
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DefaultLocationRepository(
    private val locationApisService: GeocodeApiService,
    private val geocodeService: Geocoder,
    private val locationService: FusedLocationProviderClient
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LocationCoordinates? {
        val location = locationService.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
            .await()

        if(location == null) {
            return null
        }
        return LocationCoordinates(location.latitude, location.longitude)

    }

    override suspend fun getLocationName(latitude: Double, longitude: Double): String {
        return when {
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
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return locationApisService.getLocationsByName(query)
            .map { it.asExternalModel() }
    }

}