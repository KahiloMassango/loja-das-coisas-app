package com.example.store.core.model

import androidx.annotation.DrawableRes
import com.example.store.R

data class Address(
    val id: Int,
    val receiverName: String,
    val phoneNumber: String,
    val addressType: AddressType,
    val addressLine: AddressLine,
    val latitude: Double,
    val longitude: Double,
)

data class AddressLine(
    val shortName: String,
    val address: String,
)

enum class AddressType(
    val description: String,
    @DrawableRes
    val icon: Int

) {
    HOME("Casa", R.drawable.ic_home),
    WORK("Trabalho",R.drawable.ic_briefcase),
    OTHER("Outro", R.drawable.ic_location)
}
