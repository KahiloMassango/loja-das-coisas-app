package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakeAddressRepository: AddressRepository {

    private val addresses = mutableListOf<Address>()

    override fun getAddressesStream(): Flow<List<Address>> {
        return flowOf(addresses)
    }

    override fun getLastAddedAddress(): Address? {
        return addresses.lastOrNull()
    }

    override suspend fun addAddress(address: Address) {
        addresses.add(address)
    }

    override suspend fun deleteAddressById(id: Int) {
        addresses.removeIf { it.id == id }
    }
}