package com.example.store.feature.checkout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Address

@Composable
internal fun AddressSection(
    modifier: Modifier = Modifier,
    address: Address?,
    onAddNewAddress: () -> Unit,
    onChangeAddress: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = modifier,
            text = "Endereço de entrega",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        if (address == null) {
            EmptyDeliveryAddressCard(onAddNewAddress = onAddNewAddress)
        } else {
            DeliveryAddressCard(
                address = address,
                onChangeAddress = onChangeAddress,
            )
        }
    }

}