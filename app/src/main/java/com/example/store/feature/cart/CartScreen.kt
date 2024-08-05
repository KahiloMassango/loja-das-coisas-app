package com.example.store.feature.cart

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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.cart.component.CartItemCard
import com.example.store.feature.shop.model.cartProduct

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    onCheckout: () -> Unit,
) {
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
                title = "Meu Cesto", canNavigateBack = false)
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
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                    items(16) {
                        CartItemCard(
                            modifier = Modifier,
                            product = cartProduct,
                            onRemove = { /* TODO: Implement delete */ }
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
                            text = "2500kz",
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


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartScreen{}
    }
}