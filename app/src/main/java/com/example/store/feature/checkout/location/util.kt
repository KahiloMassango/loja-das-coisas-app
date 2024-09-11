package com.example.store.feature.checkout.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

@SuppressLint("MissingPermission")
fun fetchCurrentLocation(
    locationClient: FusedLocationProviderClient,
    cameraPositionState: CameraPositionState
) {
    locationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
        .addOnCompleteListener { response ->
            if (response.result != null) {
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLng(
                        LatLng(response.result.latitude, response.result.longitude)
                    )
                )
            }
        }
}

@SuppressLint("MissingPermission", "NewApi")
fun updateLocationName(
    geocoder: Geocoder,
    latLng: LatLng,
    onLocationNameReceived: (String) -> Unit
) {
    geocoder.getFromLocation(
        latLng.latitude, latLng.longitude, Priority.PRIORITY_PASSIVE
    ) { response ->
        onLocationNameReceived(response.getOrNull(0)?.getAddressLine(0) ?: "")
    }
}
