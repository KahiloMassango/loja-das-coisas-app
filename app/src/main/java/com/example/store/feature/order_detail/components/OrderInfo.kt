package com.example.store.feature.order_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.feature.order_detail.components.OrderDescription

@Composable
fun OrderInfo(
    modifier: Modifier = Modifier,
    orderNumber: String,
    date: String,
    itemsQty: Int,
    status: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pedido №$orderNumber",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$itemsQty itens",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.scrim
            )
        }
    }
}
@Composable
fun OrderResume(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = "Informações do Pedido",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        OrderDescription(text = "Endereço de Entrega", description = "Gamek, Morro Bento")
        OrderDescription(text = "Método de pagamento", description = "MCX Express")
        OrderDescription(text = "Método de envio", description = "Entrega")
        OrderDescription(text = "Total", description = "4.500kz")
    }
}