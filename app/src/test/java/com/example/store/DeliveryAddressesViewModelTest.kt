package com.example.store

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.model.Address
import com.example.store.data.ADDRESS_1
import com.example.store.data.ADDRESS_2
import com.example.store.fakes_repositories.FakeAddressRepository
import com.example.store.feature.delivery_address.DeliveryAddressesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DeliveryAddressesViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: DeliveryAddressesViewModel
    private lateinit var repository: AddressRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeAddressRepository()
        viewModel = DeliveryAddressesViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ViewModel flow emits empty list when first loaded`() = runTest {
        val fakeRepository = FakeAddressRepository()
        val viewModel = DeliveryAddressesViewModel(fakeRepository)

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.deliveryAddresses.collect()
        }
        assertEquals(emptyList<Address>(), viewModel.deliveryAddresses.value)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ViewModel flow emits addresses after they are added`() = runTest {

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.deliveryAddresses.collect()
        }

        repository.addAddress(ADDRESS_1)
        assertEquals(listOf(ADDRESS_1), viewModel.deliveryAddresses.value)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ViewModel flow emits correct addresses after remove`() = runTest {
        repository.addAddress(ADDRESS_1)
        repository.addAddress(ADDRESS_2)

        viewModel.deleteAddress(ADDRESS_1.id)

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.deliveryAddresses.collect()
        }

        assertEquals(listOf(ADDRESS_2), viewModel.deliveryAddresses.value)

    }

}