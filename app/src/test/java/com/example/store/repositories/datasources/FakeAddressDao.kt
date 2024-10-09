package com.example.store.repositories.datasources

import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.model.AddressEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAddressDao: AddressesDao {
    private val addresses = mutableListOf<AddressEntity>()
    override fun getAddressesStream(): Flow<List<AddressEntity>> {
        return flow { emit(addresses.toList()) }
    }

    override fun getLastAddedAddress(): AddressEntity? {
        return addresses.lastOrNull()
    }

    override fun deleteAddressById(id: Int) {
        addresses.removeAll { it.id == id }
    }

    override suspend fun insertAddress(address: AddressEntity) {
        addresses.add(address)
    }
}