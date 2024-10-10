package com.example.store.data

import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType

val ADDRESS_1 = Address(
    1,
    "test1",
    "123456789",
    AddressType.HOME,
    AddressLine("Samba, Luanda", "street 1, Gamek, Luanda"),
    0.0,
    0.0
)
val ADDRESS_2 = Address(
    2,
    "test2",
    "123456789",
    AddressType.OTHER,
    AddressLine("Samba, Luanda", "street 2, Gamek, Luanda"),
    0.0,
    0.0
)
val ADDRESS_3 = Address(
    3,
    "test3",
    "123456789",
    AddressType.OTHER,
    AddressLine("Samba, Luanda", "street 3, Gamek, Luanda"),
    0.0,
    0.0
)