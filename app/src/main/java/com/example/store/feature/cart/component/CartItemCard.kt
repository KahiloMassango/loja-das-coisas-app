package com.example.store.feature.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.R
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.theme.defaultFont
import com.example.store.feature.shop.model.CartProduct
import com.example.store.feature.shop.model.cartProduct

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    product: CartProduct,
    onRemove: (String) -> Unit
) {
    var quantity by remember { mutableIntStateOf(1) }

    Card(
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
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(104.dp),
                painter = painterResource(id = R.drawable.ic_person),
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
                    Column {
                        Text(
                            text = "T-Shirt Sailing",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(
                            modifier = Modifier,
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Attribute(attribute = "Cor", value = product.color)
                            Attribute(attribute = "Tamanho", value = product.size)
                        }
                    }
                    IconButton(
                        modifier = Modifier.size(22.dp),
                        onClick = { onRemove(product.id) }
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
                    ItemQuantity(
                        quantity = quantity,
                        onDecrease = { if (quantity > 1) quantity -- },
                        onIncrease = { quantity ++ }
                    )
                    Text(
                        text = "${product.price}kz",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemQuantity(
    modifier: Modifier = Modifier,
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = modifier.width(115.dp),
        verticalAlignment = CenterVertically,
    ) {

        Surface(
            modifier = Modifier,
            shape = CircleShape,
            shadowElevation = 4.dp,
            onClick = onDecrease,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Default.Remove,
                contentDescription = null,
            )
        }
        Text(
            text = "$quantity",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)

        )
        Surface(
            modifier = Modifier,
            shape = CircleShape,
            shadowElevation = 4.dp,
            onClick = onIncrease,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
        }
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
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        ) { append("${attribute}:") }
        append(" ")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = defaultFont,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        ) { append(value) }
    }

    Text(modifier = modifier, text = stringBuilder)
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartItemCard(
            product = cartProduct,
            onRemove = {}
        )
    }
}