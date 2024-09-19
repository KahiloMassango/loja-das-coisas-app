package com.example.store.feature.select_delivery_location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.example.store.feature.select_delivery_location.components.LocationInfo
import com.example.store.feature.select_delivery_location.components.LocationSearchSheet
import com.example.store.feature.select_delivery_location.components.MapView

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
        locationCoordinates = uiState.locationCoordinates,
        query = uiState.searchQuery,
        searchResult = searchResults,
        onQueryChange = { viewModel.updateSearchQuery(it) },
        onMoveToUserLocation = viewModel::getUserCurrentLocation,
        onLocationChange = { viewModel.updateLocation(it) },
        onNavigateUp = onNavigateUp,
        onConfirmLocation = {
            viewModel.confirmDeliveryLocation()
            onNavigateUp()
        }
    )


}

@Composable
private fun SelectDeliveryLocationContent(
    modifier: Modifier = Modifier,
    locationName: String,
    locationCoordinates: LocationCoordinates,
    query: String,
    searchResult: List<Location>,
    onQueryChange: (String) -> Unit,
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
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .weight(1f),
                shadowElevation = 5.dp,
                shape = RoundedCornerShape(5)
            ){
                MapView(
                    modifier = modifier,
                    locationCoordinates = locationCoordinates,
                    onLocationChange = { coordinates ->
                        onLocationChange(coordinates)
                    },
                    onMoveToUserLocation = onMoveToUserLocation,
                )
            }

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


