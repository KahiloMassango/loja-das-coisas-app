package com.example.store.feature.select_delivery_location

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.feature.select_delivery_location.components.CustomMarker
import com.example.store.feature.select_delivery_location.components.LocationInfo
import com.example.store.feature.select_delivery_location.components.LocationSearchSheet
import com.example.store.feature.select_delivery_location.components.MyLocationButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SelectDeliveryLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectDeliveryLocationViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    val uiState by viewModel.selectDeliveryLocationUiState.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    SelectDeliveryLocationContent(
        modifier = modifier,
        locationName = uiState.locationName,
        deliveryLocation = uiState.deliveryLocation,
        query = uiState.searchQuery,
        searchResult = searchResults,
        onQueryChange = { viewModel.updateSearchQuery(it) },
        onMoveToUserLocation = viewModel::getUserCurrentLocation,
        onLocationChange = { viewModel.updateLocationName(it) },
        onNavigateUp = onNavigateUp,
        onConfirmLocation = {
            viewModel.confirmDeliveryLocation(
                LocationCoordinates(it.latitude, it.longitude)
            )
            onNavigateUp()
        }
    )


}

@Composable
private fun SelectDeliveryLocationContent(
    modifier: Modifier = Modifier,
    locationName: String,
    deliveryLocation: LocationCoordinates,
    query: String,
    searchResult: List<Location>,
    onQueryChange: (String) -> Unit,
    onMoveToUserLocation: () -> Unit,
    onLocationChange: (LocationCoordinates) -> Unit,
    onConfirmLocation: (LocationCoordinates) -> Unit,
    onNavigateUp: () -> Unit
) {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 17.5f)
    }

    // Run when user press use my location button
    // Or when the order delivery location is loaded from database
    LaunchedEffect(deliveryLocation) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
                LatLng(deliveryLocation.latitude, deliveryLocation.longitude),
            17.5f
            )

    }

    // Run when user change location in map
    // Launch this to get the location name
    LaunchedEffect(cameraPositionState.isMoving) {
        if (! cameraPositionState.isMoving &&
            deliveryLocation.latitude != cameraPositionState.position.target.latitude
        ) {
            onLocationChange(
                LocationCoordinates(
                    latitude = cameraPositionState.position.target.latitude,
                    longitude = cameraPositionState.position.target.longitude
                )
            )
        }
    }

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = "Selecionar localização",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                action = {
                    IconButton(onClick = { isSearching = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .navigationBarsPadding()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .weight(1f),
                shadowElevation = 5.dp,
                shape = RoundedCornerShape(5)
            ) {
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
                    MyLocationButton(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        onClick = onMoveToUserLocation
                    )
                    CustomMarker(Modifier.align(Alignment.Center))
                }
            }

            LocationInfo(
                locationName = locationName,
                onConfirmLocation = {
                    onConfirmLocation(
                        LocationCoordinates(
                            latitude = cameraPositionState.position.target.latitude,
                            longitude = cameraPositionState.position.target.longitude
                        )
                    )
                }
            )

        }
        if (isSearching) {
            LocationSearchSheet(
                query = query,
                onQueryChange = { onQueryChange(it) },
                searchResult = searchResult,
                onSelectLocation = { coordinates ->
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLng(
                                LatLng(
                                    coordinates.latitude,
                                    coordinates.longitude
                                )
                            )
                        )
                    }
                    isSearching = false
                },
                onUseUserCurrentLocation = {
                    onMoveToUserLocation()
                    isSearching = false
                },
                onDismissRequest = { isSearching = false },
            )
        }

    }
}


