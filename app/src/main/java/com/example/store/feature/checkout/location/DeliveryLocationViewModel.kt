package com.example.store.feature.checkout.location

import android.util.Log
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState = _uiState.asStateFlow()



    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = uiState
        .map { it.searchQuery }
        .debounce(500)
        .filter { it.isNotEmpty() }
        .mapLatest { query ->
            Log.d("DeliveryLocationViewModel", "search: requested")
            locationRepository.searchLocation(query)

        }
        .catch { e ->
            Log.d("DeliveryLocationViewModel", "Error fetching search results: ${e.message}")
            emptyList<Location>() // Or emit a specific error state
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun updateSearchQuery(query: String){
        _uiState.update { currentState ->
            currentState.copy(searchQuery = query)
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            val location = locationRepository.getCurrentLocation()
            if(location != null){
                selectLocation(LocationCoordinates(location.latitude, location.longitude))
            }
        }
    }

    fun selectLocation(coordinates: LocationCoordinates){
        viewModelScope.launch {
            _uiState.value = LocationUiState(
                location = LocationCoordinates(coordinates.latitude, coordinates.longitude),
                locationName = locationRepository
                    .getLocationName(coordinates.latitude, coordinates.longitude)
            )
        }
    }

}

data class LocationUiState(
    val locationName: String = "",
    val location: LocationCoordinates = LocationCoordinates(-8.8271, 13.2439),
    val searchQuery: String = ""
)