package com.example.store.feature.checkout.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun ShippingAddressCard(
    modifier: Modifier = Modifier,
    username: String,
    address: String,
    onChangeAddress: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = username,
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
                text = address,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ShippingAddressCard(
            username = "Jane Doe",
            address = "3 Newbridge Court Chino Hills, CA 91709, United States",
            onChangeAddress = { /*TODO*/ },
        )
    }
}