package com.example.store.feature.checkout.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
internal fun DeliveryMethodSection(
    modifier: Modifier = Modifier,
    selectedMethod: DeliveryMethod,
    pickUpDeliveryPrice: Int,
    onSelectDeliveryMethod: (DeliveryMethod, String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = modifier,
            text = "Método de entrega",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            DeliveryMethodCard(
                title = "Entrega",
                description = "$pickUpDeliveryPrice kz",
                selected = selectedMethod == DeliveryMethod.DELIVERY,
                onClick = { onSelectDeliveryMethod(DeliveryMethod.DELIVERY, "${pickUpDeliveryPrice}Kz") }
            )
            DeliveryMethodCard(
                title = "Levantar",
                description = "Grátis",
                selected = selectedMethod == DeliveryMethod.PICKUP,
                onClick = { onSelectDeliveryMethod(DeliveryMethod.PICKUP, "Grátis") }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeliveryMethodCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.inverseSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
