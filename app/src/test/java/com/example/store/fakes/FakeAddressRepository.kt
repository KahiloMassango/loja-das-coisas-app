package com.example.store.fakes

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeAddressRepository: AddressRepository {
    val addresses = mutableListOf<Address>()
    override fun getAllAddressesStream(): Flow<List<Address>> {
        return flow { emit(addresses) }
    }

    override fun getLastAddedAddress(): Address {
        return addresses.last()
    }

    override fun getAddressByIdStream(id: Int): Flow<Address> {
        return flowOf(addresses.find { it.id == id }!!)
    }

    override fun getAddressById(id: Int): Address? {
        return addresses.find { it.id == id }
    }

    override suspend fun addAddress(address: Address) {
        addresses.add(address)
    }

    override suspend fun deleteAddressById(id: Int) {
        addresses.removeIf({ it.id == id })
    }
}