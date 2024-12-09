package com.example.store.features.cart.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.store.core.model.cart.CartProduct
import com.example.store.core.model.cart.cartProduct
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.theme.defaultFont

@ExperimentalMaterial3Api
@Composable
internal fun CartProductCard(
    modifier: Modifier = Modifier,
    product: CartProduct,
    onClick: (String) -> Unit,
    onIncreaseQty: () -> Unit,
    onDecreaseQty: () -> Unit,
    onRemove: (String) -> Unit
) {

    Card(
        onClick = { onClick(product.productItemId) },
        modifier = modifier.height(104.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = product.name,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(0.dp),
                        ) {
                            if (product.color != null) {
                                Attribute(attribute = "Cor", value = product.color!!)
                            }
                            if (product.size != null) {
                                Attribute(attribute = "Tamanho", value = product.size!!)
                            }
                        }
                    }
                    IconButton(
                        modifier = Modifier.size(22.dp),
                        onClick = { onRemove(product.productItemId) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuantitySelector(
                        quantity = product.quantity,
                        onDecrease = { if (product.quantity > 1) onDecreaseQty() },
                        onIncrease = onIncreaseQty
                    )
                    Text(
                        text = "${product.price}kz",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun QuantitySelector(
    modifier: Modifier = Modifier,
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = modifier
            .width(85.dp),
        verticalAlignment = CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp)
                .clickable(null, null) { onDecrease() },
            imageVector = Icons.Default.Remove,
            contentDescription = null,
        )

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)

        )

        Icon(
            modifier = Modifier
                .size(26.dp)
                .clickable(null, null) { onIncrease() },
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}

@Composable
private fun Attribute(
    modifier: Modifier = Modifier,
    attribute: String,
    value: String
) {
    val stringBuilder = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontFamily = defaultFont,
                fontWeight = FontWeight.Normal
            )
        ) { append("${attribute}:") }
        append(" ")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = defaultFont,
                fontWeight = FontWeight.SemiBold
            )
        ) { append(value) }
    }

    Text(
        modifier = modifier,
        text = stringBuilder,
        style = MaterialTheme.typography.labelSmall,
        fontSize = 10.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartProductCard(
            product = cartProduct,
            onClick = { },
            onRemove = {},
            onIncreaseQty = {},
            onDecreaseQty = {}
        )
    }
}