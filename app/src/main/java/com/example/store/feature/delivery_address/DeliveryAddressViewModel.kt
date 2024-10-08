package com.example.store.feature.delivery_address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryAddressesViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
) : ViewModel() {

    val deliveryAddresses = addressRepository.getAllAddressesStream()
        .hotFlow(viewModelScope, initialValue = emptyList())


    fun deleteAddress(addressId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addressRepository.deleteAddressById(addressId)
            } catch (e: Exception) {
                Log.d("DeliveryAddressesViewModel", "deleteAddress: $e")
            }
        }
    }

}

fun <T> Flow<T>.hotFlow(scope: CoroutineScope, initialValue: T): StateFlow<T> {
    return this.stateIn(
        scope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue
    )
}