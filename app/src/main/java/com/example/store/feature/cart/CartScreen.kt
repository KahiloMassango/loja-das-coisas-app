package com.example.store.feature.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.R
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.cart.component.CartProductCard

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel(),
    onCheckout: () -> Unit,
) {
    val products by viewModel.uiState.collectAsStateWithLifecycle()
    val cartTotal by viewModel.cartTotal.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            StoreLargeTopBar(
                modifier = Modifier
                    .background(Color.Red)
                    .clip(GenericShape { size, _ ->
                        lineTo(size.width, 0f)
                        lineTo(size.width, Float.MAX_VALUE)
                        lineTo(0f, Float.MAX_VALUE)
                    })
                    .shadow(8.dp),
                title = "Meu Cesto", canNavigateBack = false
            )
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),

                ) {
                if (products.isEmpty()) {
                    EmptyCartScreen()
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        items(products) { product ->
                            CartProductCard(
                                modifier = Modifier,
                                product = product,
                                onRemove = { viewModel.removeProductFromCart(it) },
                                onIncreaseQty = {
                                    viewModel.updateQuantity(
                                        product.id,
                                        product.quantity + 1
                                    )
                                },
                                onDecreaseQty = {
                                    viewModel.updateQuantity(
                                        product.id,
                                        product.quantity - 1
                                    )
                                }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(bottom = 10.dp, top = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Montante total:",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                            )
                            Text(
                                text = "%.2f".format(cartTotal),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        CustomButton(
                            text = "Pagar",
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onCheckout
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyCartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.empty_cart),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Seu cesto está vazio",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Parece que você não adicionou nada ao seu cesto. Vá em frente e explore as principais categorias.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartScreen {}
    }
}