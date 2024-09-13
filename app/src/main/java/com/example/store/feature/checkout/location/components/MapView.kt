package com.example.store.feature.checkout.location.components

import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.store.core.model.LocationCoordinates
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    locationCoordinates: LocationCoordinates,
    onLocationChange: (LocationCoordinates) -> Unit,
    onMoveToUserLocation: () -> Unit
) {

    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(locationCoordinates.latitude, locationCoordinates.longitude),
            17.5f
        )
    }

    // Launch when user press current location button
    LaunchedEffect(locationCoordinates) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(locationCoordinates.latitude, locationCoordinates.longitude)
            )
        )
    }

    // Launch effect to observe camera movement
    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            val coordinates = cameraPositionState.position.target
            onLocationChange(
                LocationCoordinates(
                    latitude = coordinates.latitude,
                    longitude = coordinates.longitude
                )
            )
        }
    }


    Box(modifier = modifier) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        )

        IconButton(
            modifier = Modifier
                .padding(bottom = 30.dp, end = 16.dp)
                .align(Alignment.BottomEnd)

                .shadow(10.dp, CircleShape),
            colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondaryContainer),
            onClick = {
                if (context.isGpsEnabled()) {
                    onMoveToUserLocation()
                } else {
                    Toast.makeText(
                        context,
                        "Active o GPS",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.GpsFixed,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        CustomMarker(Modifier.align(Alignment.Center))
    }
}


private fun Context.isGpsEnabled(): Boolean {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}
