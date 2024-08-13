package com.example.store.feature.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.checkout.component.AddressSection
import com.example.store.feature.checkout.component.CheckoutSummary
import com.example.store.feature.checkout.component.DeliveryMethod
import com.example.store.feature.checkout.component.DeliveryMethodSection


@Composable
internal fun CheckoutScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) {
    var selectedDeliveryMethod by rememberSaveable { mutableStateOf(DeliveryMethod.DELIVERY) }
    var deliveryPrice by rememberSaveable { mutableStateOf("2.700") }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Finalizar Compra",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
                //elevation = 5.dp
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues),
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(15.dp),
            ) {
                AddressSection(
                    username = "Jane Doe",
                    address = "3 Newbridge Court Chino Hills, CA 91709, United States",
                    onChangeAddress = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(42.dp))

                DeliveryMethodSection(
                    pickUpDeliveryPrice = 12000,
                    selectedMethod = selectedDeliveryMethod,
                    onSelectDeliveryMethod = { method, price ->
                        selectedDeliveryMethod = method
                        deliveryPrice = price
                    }
                )

                Spacer(modifier = Modifier.height(42.dp))

                CheckoutSection(text = "Forma de pagamento")

                Spacer(modifier = Modifier.weight(1f))

                CheckoutSummary(
                    cartTotal = 12000,
                    deliveryPrice = deliveryPrice,
                    totalSummary = 13450,
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Finalizar Compra",
                    onClick = { /*TODO*/ },
                )
            }
        }
    }
}





@Composable
private fun CheckoutSection(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
    )
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        CheckoutScreen(
            onNavigateUp = {},
        )
    }
}
