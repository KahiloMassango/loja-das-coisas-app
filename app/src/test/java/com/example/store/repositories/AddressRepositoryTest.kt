package com.example.store.repositories

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.DefaultAddressRepository
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.model.Address
import com.example.store.data.ADDRESS_1
import com.example.store.data.ADDRESS_2
import com.example.store.data.ADDRESS_3
import com.example.store.repositories.datasources.FakeAddressDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddressRepositoryImplTest {

    lateinit var addressDao: AddressesDao
    lateinit var repository: AddressRepository
    @Before
    fun setUp() {
      addressDao = FakeAddressDao()
      repository = DefaultAddressRepository(addressDao)
    }

    @Test
    fun repository_first_created_state_is_empty() = runTest {
        assertEquals(emptyList<Address>(), repository.getAddressesStream().first())
    }

    @Test
    fun repository_add_address_correctly() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_2)

        assertEquals(listOf(ADDRESS_1,ADDRESS_2), repository.getAddressesStream().first())
    }

    @Test
    fun repository_delete_address_correctly() = runTest {
        repository.addAddress(ADDRESS_1)
        //assertEquals(ADDRESS_1, repository.getLastAddedAddress())

        repository.deleteAddressById(1)
        assertEquals(emptyList<Address>(), repository.getAddressesStream().first())
    }

    @Test
    fun repository_returns_correct_last_address() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_3)

        assertEquals(ADDRESS_3, repository.getLastAddedAddress())
    }

    @Test
    fun getLastAddress_returns_null_when_no_address() = runTest {
        assertNull(repository.getLastAddedAddress())
    }


}