package com.example.store.core.data.model

import com.example.store.core.database.model.AddressEntity
import com.example.store.core.database.model.OrderEntity
import com.example.store.core.model.Address
import com.example.store.core.model.Order


fun Order.asEntity() = OrderEntity(
    id,
    cartTotal,
    deliveryFee,
    orderTotal,
    deliveryLocationName,
    latitude,
    longitude,
    paymentType,
    deliveryMethod
)


fun Address.asEntity(): AddressEntity = AddressEntity(
    id = id,
    receiverName = receiverName,
    phoneNumber = phoneNumber,
    addressType = addressType,
    addressLine = addressLine.address,
    shortName = addressLine.shortName,
    latitude = latitude,
    longitude = longitude
)



