package com.example.store.fakes_repositories

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow


class FakeAddressRepository: AddressRepository {

    private val addresses = MutableStateFlow<List<Address>>(emptyList())

    override fun getAddressesStream(): Flow<List<Address>> = addresses

    override fun getLastAddedAddress(): Address {
        return addresses.value.last()
    }

    override suspend fun addAddress(address: Address) {
        addresses.value = addresses.value + address
    }

    override suspend fun deleteAddressById(id: Int) {
        addresses.value = addresses.value.filter { it.id != id }
    }
}
