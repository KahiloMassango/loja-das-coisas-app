package com.example.store.feature.checkout

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.model.DeliveryMethod
import com.example.store.core.model.Location
import com.example.store.core.model.LocationCoordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {

    private val _deliveryMethod = MutableStateFlow(DeliveryMethod.DELIVERY)
    val deliveryMethod = _deliveryMethod.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    var deliveryPrice = 12000.00

    var deliveryLocation = mutableStateOf(
        Location(
            name = "56FV+4HV, Luanda, Angola",
            coordinates = LocationCoordinates(- 8.8271, 13.2439)
        )
    )
        private set


    val cartTotal = cartRepository.getCartTotal()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val orderTotal = deliveryMethod
        .combine(cartTotal) { method, total ->
            when (method) {
                DeliveryMethod.DELIVERY -> deliveryPrice + total
                DeliveryMethod.PICKUP -> total
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0.0
        )


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = searchQuery
        .debounce(500)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .mapLatest { query ->
            try {
                locationRepository.searchLocation(query).also {
                    Log.d("DeliveryLocationViewModel", "Search: Requested")
                }
            } catch (e: Exception) {
                Log.e("DeliveryLocationViewModel", "Error fetching search results", e)
                emptyList()
            }
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

    fun setDeliveryMethod(method: DeliveryMethod) {
        _deliveryMethod.value = method
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    fun setUserCurrentLocation() {
        viewModelScope.launch {
            val location = locationRepository.getCurrentLocation()
            if (location != null) {
                updateLocation(
                    LocationCoordinates(
                        location.latitude, location.longitude
                    )
                )
            }
        }
    }

    fun updateLocation(coordinates: LocationCoordinates) {
        viewModelScope.launch {
            deliveryLocation.value = Location(
                coordinates = coordinates,
                name = locationRepository
                    .getLocationName(coordinates.latitude, coordinates.longitude)
            )
        }
    }

}