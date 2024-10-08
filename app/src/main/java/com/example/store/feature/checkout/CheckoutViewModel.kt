package com.example.store.feature.checkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.Address
import com.example.store.core.model.DeliveryMethod
import com.example.store.core.model.Order
import com.example.store.core.model.resource.calculateOrderTotal
import com.example.store.feature.delivery_address.hotFlow
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val locationRepository: LocationRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val deliveryPricePerKM = 150.0
    val deliveryAddresses = addressRepository.getAllAddressesStream()
        .hotFlow(viewModelScope, emptyList())

    private var _currentDeliveryAddress: MutableStateFlow<Address?> = MutableStateFlow(null)
    val currentDeliveryAddress = _currentDeliveryAddress
        .onStart { loadInitialData() }
        .hotFlow(viewModelScope, null)



    fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               _currentDeliveryAddress.value = addressRepository.getLastAddedAddress()
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error fetching delivery addresses: ${e.message}", e)
            }
        }
    }

    val order = orderRepository.getOrderStream()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            Order()
        )


    fun changeDeliveryAddress(address: Address) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currentDeliveryAddress.value = address
                calculateDeliveryPrice()
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error changing delivery address: ${e.message}", e)
            }
        }
    }

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
                    origin = LatLng(- 8.893632, 13.188212),
                    destination = LatLng(order.latitude, order.longitude)
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

}
