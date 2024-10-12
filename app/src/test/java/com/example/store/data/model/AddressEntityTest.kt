package com.example.store.data.model

import com.example.store.core.data.model.asEntity
import com.example.store.testdata.ADDRESS_1
import org.junit.Test
import org.junit.Assert.assertEquals


class AddressEntityTest {

    @Test
    fun `AddressEntity can be mapped to address entity`() {
        val address = ADDRESS_1

        val entity = address.asEntity()

        assertEquals(address.id, entity.id)
        assertEquals(address.receiverName, entity.receiverName)
        assertEquals(address.phoneNumber, entity.phoneNumber)
        assertEquals(address.addressType, entity.addressType)
        assertEquals(address.addressLine.shortName, entity.shortName)
        assertEquals(address.addressLine.address, entity.addressLine)
        assertEquals(address.latitude, entity.latitude, 0.0)
        assertEquals(address.longitude, entity.longitude, 0.0)

    }
}