package com.example.store.presentation.screens.order_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.screens.order_detail.components.DetailOrderProductCard
import com.example.store.presentation.screens.order_detail.components.OrderInfo
import com.example.store.presentation.screens.order_detail.components.OrderResume
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun OrderDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val scrollState = rememberScrollState() // Hoist scroll state

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Detalhes do Pedido", // Use string resources
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
                // Elevation is usually handled by the theme
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
            //.verticalScroll(scrollState) // Use hoisted scroll state
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    item {
                        OrderInfo(
                            orderNumber = "1947034",
                            date = "05-12-2019",
                            itemsQty = 3,
                            status = "Entregue"
                        )
                    }

                    item { Spacer(modifier = Modifier.height(18.dp)) }

                    items(7) {
                        DetailOrderProductCard(modifier = Modifier.padding(bottom = 22.dp))
                    }

                    item { Spacer(modifier = Modifier.height(12.dp)) }

                    item { OrderResume() }
                    item { Spacer(modifier = Modifier.height(12.dp)) }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        OrderDetailScreen(
            onNavigateUp = {}
        )
    }
}