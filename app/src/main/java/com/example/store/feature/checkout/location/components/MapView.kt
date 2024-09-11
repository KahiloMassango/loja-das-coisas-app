package com.example.store.feature.checkout.location.components

import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    modifier: Modifier,
    currentLocation: LocationCoordinates,
    onLocationChange: (LocationCoordinates) -> Unit,
    onMoveToUserLocation: () -> Unit
) {

    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(currentLocation.latitude, currentLocation.longitude),
            16.2f
        )
    }

    // Launch when user press current location button
    LaunchedEffect(currentLocation) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(currentLocation.latitude, currentLocation.longitude)
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


    Box(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(5))
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true,
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            ),
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp, end = 16.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
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
