package com.example.store.feature.checkout.location.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import com.example.store.core.ui.component.StoreCenteredTopBar

@Composable
fun SelectDeliveryLocationContent(
    modifier: Modifier = Modifier,
    locationName: String,
    currentLocation: LocationCoordinates,
    query: String,
    onQueryChange: (String) -> Unit,
    searchResult: List<Location>,
    onMoveToUserLocation: () -> Unit,
    onLocationChange: (LocationCoordinates) -> Unit,
    onConfirmLocation: () -> Unit,
    onNavigateUp: () -> Unit
) {
    var isSearching by rememberSaveable { mutableStateOf(false) }

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
            MapView(
                modifier = modifier.weight(1f),
                currentLocation = currentLocation,
                onLocationChange = { coordinates ->
                    onLocationChange(coordinates)
                },
                onMoveToUserLocation = onMoveToUserLocation,
            )

            LocationInfo(
                locationName = locationName,
                onConfirmLocation = onConfirmLocation
            )

        }
        if (isSearching) {
            LocationSearchSheet(
                query = query,
                onQueryChange = { onQueryChange(it) },
                searchResult = searchResult,
                onSelectLocation = { coordinates ->
                    onLocationChange(coordinates)
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
