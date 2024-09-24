package com.example.store.feature.checkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.DeliveryMethod
import com.example.store.core.model.LocationCoordinates
import com.example.store.core.model.Order
import com.example.store.core.model.resource.calculateOrderTotal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val order = orderRepository.getOrderStream()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            Order()
        )


    private val deliveryPricePerKM = 150.0

    fun setDeliveryMethod(method: DeliveryMethod) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                orderRepository.updateDeliveryMethod(method)
                val currentOrder = order.value
                orderRepository.updateOrderTotal(
                    calculateOrderTotal(
                        currentOrder.deliveryFee,
                        currentOrder.cartTotal,
                        method
                    )
                )
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error setting delivery method: ${e.message}", e)
            }
        }
    }

    fun calculateDeliveryPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val order = orderRepository.getOrder()
                val distance = locationRepository.getLocationDistance(
                    origin = LocationCoordinates(- 8.893632, 13.188212),
                    destination = LocationCoordinates(order.latitude, order.longitude)
                ) ?: 0.0
                val deliveryPrice = distance * deliveryPricePerKM
                orderRepository.updateDeliveryFee(deliveryPrice)
                orderRepository.updateOrderTotal(
                    calculateOrderTotal(
                        deliveryPrice,
                        order.cartTotal,
                        order.deliveryMethod
                    )
                )
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error calculating delivery price: ${e.message}", e)
            }
        }
    }

    fun setUserLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userLocation = locationRepository.getCurrentLocation()
                if (userLocation != null) {
                    orderRepository.changeDeliveryLocation(
                        locationRepository.getLocationName(
                            userLocation.latitude,
                            userLocation.longitude
                        ),
                        userLocation.latitude,
                        userLocation.longitude
                    )
                    calculateDeliveryPrice()
                }
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error getting user location: ${e.message}", e)
            }
        }
    }

}
