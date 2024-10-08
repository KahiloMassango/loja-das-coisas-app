package com.example.store.feature.checkout.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Address
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme


@Composable
fun DeliveryAddressCard(
    modifier: Modifier = Modifier,
    address: Address,
    onChangeAddress: () -> Unit
) {
    Card(
        modifier = modifier
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = address.receiverName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier.clickable { onChangeAddress() },
                    text = "Alterar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

            }
            Text(
                text = address.addressLine.address,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "+244 ${address.phoneNumber}",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun EmptyDeliveryAddressCard(
    modifier: Modifier = Modifier,
    onAddNewAddress: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = onAddNewAddress,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aicionar novo endereço",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        /*DeliveryAddressCard(
            username = "Jane Doe",
            address = "3 Newbridge Court Chino Hills, CA 91709, United States",
            onChangeAddress = { *//*TODO*//* },
        )*/
    }
}