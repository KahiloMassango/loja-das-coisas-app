package com.example.store.fakes_repositories

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.database.model.AddressEntity
import com.example.store.core.model.Address
import com.example.store.data.ADDRESS_1
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class FakeAddressRepository: AddressRepository {
    private val flow = MutableSharedFlow<List<Address>>()
    private val addresses = mutableListOf<Address>(ADDRESS_1)
    suspend fun emit() = flow.emit(listOf(ADDRESS_1))

    override fun getAllAddressesStream(): Flow<List<Address>> = flow

    override fun getLastAddedAddress(): Address {
        return addresses.last()
    }

    override suspend fun addAddress(address: Address) {
        addresses.add(address)
    }

    override suspend fun deleteAddressById(id: Int) {
        addresses.removeAll { it.id == id }
    }
}