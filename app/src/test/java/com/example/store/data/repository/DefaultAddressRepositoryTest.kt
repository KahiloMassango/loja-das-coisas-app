package com.example.store.data.repository

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.DefaultAddressRepository
import com.example.store.core.model.Address
import com.example.store.data.testdoubles.FakeAddressDao
import com.example.store.testdata.ADDRESS_1
import com.example.store.testdata.ADDRESS_2
import com.example.store.testdata.ADDRESS_3
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultAddressRepositoryTest {

    lateinit var repository: AddressRepository

    @Before
    fun setUp() {
      val addressDao = FakeAddressDao()
      repository = DefaultAddressRepository(addressDao)
    }

    @Test
    fun getAddressesStream_WhenCalled_ReturnsEmptyListInitially() = runTest {
        assertEquals(emptyList<Address>(), repository.getAddressesStream().first())
    }

    @Test
    fun getAddressesStream_WhenCalled_ReturnsUpdatedList() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_2)

        assertEquals(listOf(ADDRESS_1,ADDRESS_2), repository.getAddressesStream().first())
    }

    @Test
    fun deleteAddressById_WhenAddressRemoved_ReturnsUpdatedList() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_2)

        repository.deleteAddressById(ADDRESS_1.id)

        assertEquals(listOf(ADDRESS_2), repository.getAddressesStream().first())
    }

    @Test
    fun getLastAddedAddress_WhenCalled_ReturnsLastAddedAddressAdded() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_3)

        assertEquals(ADDRESS_3, repository.getLastAddedAddress())
    }

    @Test
    fun getLastAddedAddress_WhenNoAddressAdded_ReturnsNull() = runTest {
        assertNull(repository.getLastAddedAddress())
    }

    @Test
    fun addAddress_WhenAddressAdded_ReturnsUpdatedList() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_2)

        assertEquals(listOf(ADDRESS_1,ADDRESS_2), repository.getAddressesStream().first())
    }



}