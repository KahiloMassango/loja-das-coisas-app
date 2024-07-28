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
fun DeliveryMethodSelector(
    modifier: Modifier = Modifier,
    deliveryPrice: Int = 500,
) {
    Row(
        modifier = modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        DeliveryMethodCard(
            title = "Entrega",
            description = "$deliveryPrice kz",
            selected = true,
            onClick = { /*TODO*/ }
        )
        DeliveryMethodCard(
            title = "Levantar",
            description = "Grátis",
            selected = false,
            onClick = { /*TODO*/ }
        )
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
