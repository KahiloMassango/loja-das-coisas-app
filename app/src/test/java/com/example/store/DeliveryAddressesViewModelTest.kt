package com.example.store

import com.example.store.core.model.Address
import com.example.store.fakes_repositories.FakeAddressRepository
import com.example.store.feature.delivery_address.DeliveryAddressesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test


class DeliveryAddressesViewModelTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delivery address is empty when first loaded`() = runTest {
        val fakeRepository = FakeAddressRepository()
        val viewModel = DeliveryAddressesViewModel(fakeRepository)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.deliveryAddresses.collect()
        }

        val deliveryAddresses = viewModel.deliveryAddresses.value
        assertEquals(deliveryAddresses, emptyList<Address>())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Delivery address not empty when there are  address`() = runTest {


    }
}