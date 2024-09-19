package com.example.store.feature.select_delivery_location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.LocationCoordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectDeliveryLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _selectDeliveryLocationUiState = MutableStateFlow(SelectDeliveryLocationState())
    val selectDeliveryLocationUiState = _selectDeliveryLocationUiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val order = orderRepository.getOrder()
            _selectDeliveryLocationUiState.update { currentState ->
                currentState.copy(
                    locationName = order.deliveryLocationName,
                    locationCoordinates = LocationCoordinates(order.latitude, order.longitude)
                )
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = selectDeliveryLocationUiState
        .debounce(800)
        .map { it.searchQuery }
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .mapLatest { query ->
            try {
                locationRepository.searchLocation(query)
            } catch (e: Exception) {
                Log.e("DeliveryLocationViewModel", "Error fetching search results: $e", e)
                emptyList()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun updateSearchQuery(query: String) {
        _selectDeliveryLocationUiState.update { currentState ->
            currentState.copy(searchQuery = query)
        }
    }

    fun getUserCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            val location = locationRepository.getCurrentLocation()
            if (location != null) {
                updateLocation(
                    LocationCoordinates(location.latitude, location.longitude)
                )
            }
        }
    }

    fun updateLocation(coordinates: LocationCoordinates) {
        viewModelScope.launch(Dispatchers.IO) {
            val locationName = locationRepository.getLocationName(
                coordinates.latitude, coordinates.longitude
            )
            _selectDeliveryLocationUiState.update { currentState ->
                currentState.copy(
                    locationName = locationName,
                    locationCoordinates = coordinates
                )
            }
        }
    }

    fun confirmDeliveryLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.changeDeliveryLocation(
                selectDeliveryLocationUiState.value.locationName,
                selectDeliveryLocationUiState.value.locationCoordinates.latitude,
                selectDeliveryLocationUiState.value.locationCoordinates.longitude
            ) }
    }


}


data class SelectDeliveryLocationState(
    val locationName: String = "",
    val locationCoordinates: LocationCoordinates = LocationCoordinates(0.0, 0.0),
    val searchQuery: String = "",
)
