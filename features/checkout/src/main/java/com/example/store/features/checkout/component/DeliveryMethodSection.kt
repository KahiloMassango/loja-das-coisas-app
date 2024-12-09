package com.example.store.features.checkout.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.model.cart.DeliveryMethod
import com.example.store.core.ui.theme.StoreTheme


@Composable
internal fun DeliveryMethodSection(
    modifier: Modifier = Modifier,
    deliveryMethod: DeliveryMethod,
    deliveryPrice: Double,
    onSelectDeliveryMethod: (DeliveryMethod) -> Unit
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
                type = "Entrega",
                description = "${deliveryPrice}kz",
                selected = deliveryMethod == DeliveryMethod.DELIVERY,
                onClick = { onSelectDeliveryMethod(DeliveryMethod.DELIVERY) }
            )
            DeliveryMethodCard(
                type = "Levantar",
                description = "Grátis",
                selected = deliveryMethod == DeliveryMethod.PICKUP,
                onClick = { onSelectDeliveryMethod(DeliveryMethod.PICKUP)}
            )
        }
    }
}



@Composable
private fun DeliveryMethodCard(
    modifier: Modifier = Modifier,
    description: String,
    type: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = type,
                style = MaterialTheme.typography.bodyLarge,
               // fontWeight = FontWeight.Normal,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
               // fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)){
            DeliveryMethodCard(
                type = "Entrega",
                description = "Grátis",
                selected = true,
                onClick = {}
            )
            DeliveryMethodCard(
                type = "Levantar",
                description = "1.200Kz",
                selected = false,
                onClick = {}
            )
        }
    }
}

