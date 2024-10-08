package com.example.store

import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.fakes.FakeAddressRepository
import com.example.store.feature.delivery_address.DeliveryAddressesViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DeliveryAddressesViewModelTest {


    lateinit var deliveryAddressesViewModel: DeliveryAddressesViewModel
    lateinit var addressRepository: AddressRepository

    @Before
    fun setUp() {
        val newAddress = Address(
            0,
            "test",
            "123456789",
            AddressType.HOME,
            AddressLine("Samba, Luanda", "Rua 7, Gamek, Luanda"),
            0.0,
            0.0
        )
        addressRepository = FakeAddressRepository()
        runBlocking { addressRepository.addAddress(newAddress) }
        deliveryAddressesViewModel = DeliveryAddressesViewModel(addressRepository)
    }

    @Test
    fun `delivery address is empty when first loaded`() = runBlocking {
        val deliveryAddresses = deliveryAddressesViewModel.deliveryAddresses.first()
        assertEquals(deliveryAddresses, emptyList<Address>())
    }

    @Test
    fun `delete existing address by id`() = runBlocking {
        val deleted = deliveryAddressesViewModel.deliveryAddresses
       // deliveryAddressesViewModel.deleteAddress(0)



    }

    @Test
    fun `Delivery address not empty when added address`() = runBlocking {
        val newAddress = Address(
            0,
            "test",
            "123456789",
            AddressType.HOME,
            AddressLine("Samba, Luanda", "Rua 7, Gamek, Luanda"),
            0.0,
            0.0
        )
        val addedAddresses = listOf(newAddress)
        val deliveryAddress = deliveryAddressesViewModel.deliveryAddresses.value

    }
}