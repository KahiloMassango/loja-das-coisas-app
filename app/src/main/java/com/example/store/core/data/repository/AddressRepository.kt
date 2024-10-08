package com.example.store.core.data.repository

import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAllAddressesStream(): Flow<List<Address>>
    fun getLastAddedAddress(): Address
    fun getAddressByIdStream(id: Int): Flow<Address>
    fun getAddressById(id: Int): Address?
    suspend fun addAddress(address: Address)
    suspend fun deleteAddressById(id: Int)
}