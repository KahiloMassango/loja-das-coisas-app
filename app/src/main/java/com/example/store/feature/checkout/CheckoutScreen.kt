package com.example.store.feature.checkout

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.Address
import com.example.store.core.model.DeliveryMethod
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.feature.checkout.component.AddressSection
import com.example.store.feature.checkout.component.CheckoutSectionText
import com.example.store.feature.checkout.component.CheckoutSummary
import com.example.store.feature.checkout.component.DeliveryAddressList
import com.example.store.feature.checkout.component.DeliveryMethodSection
import com.example.store.feature.checkout.component.RequestLocationPermissionScreen


@Composable
internal fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = hiltViewModel(),
    onAddAddress: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    var isPermissionGranted by remember {
        mutableStateOf(
            context.checkSelfPermission(locationPermission)
                    == PackageManager.PERMISSION_GRANTED
        )
    }

    val requestPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isPermissionGranted = isGranted
        if (isGranted) {
            //viewModel.setUserLocation()
        }
    }
    LaunchedEffect(null) {
        viewModel.calculateDeliveryPrice()
    }

    val order by viewModel.order.collectAsStateWithLifecycle()
    val currentDeliveryAddress by viewModel.currentDeliveryAddress.collectAsStateWithLifecycle()
    val deliveryAddresses by viewModel.deliveryAddresses.collectAsStateWithLifecycle()

    if (isPermissionGranted) {
        CheckoutContent(
            modifier = modifier,
            deliveryAddress = currentDeliveryAddress,
            deliveryAddresses = deliveryAddresses,
            deliveryPrice = order.deliveryFee,
            cartTotal = order.cartTotal,
            orderTotal = order.orderTotal,
            deliveryMethod = order.deliveryMethod,
            onNavigateUp = onNavigateUp,
            onDeliveryMethodChange = { viewModel.setDeliveryMethod(it) },
            onAddAddress = onAddAddress,
            onChangeDeliveryAddress = { viewModel.changeDeliveryAddress(it) }
        )
    } else {
        RequestLocationPermissionScreen(
            onGrant = { requestPermission.launch(locationPermission) },
        )
    }

}

@Composable
private fun CheckoutContent(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    deliveryAddresses: List<Address>,
    deliveryAddress: Address?,
    deliveryPrice: Double,
    deliveryMethod: DeliveryMethod,
    onDeliveryMethodChange: (DeliveryMethod) -> Unit,
    onChangeDeliveryAddress: (Address) -> Unit,
    onAddAddress: () -> Unit,
    cartTotal: Double,
    orderTotal: Double
) {
    val sectionSpacing = 42.dp
    var changeDeliveryAddress by rememberSaveable { mutableStateOf(false) }
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
            ) {
                AddressSection(
                    address = deliveryAddress,
                    onAddNewAddress = onAddAddress,
                    onChangeAddress = { changeDeliveryAddress = true }
                )

                Spacer(modifier = Modifier.height(sectionSpacing))

                DeliveryMethodSection(
                    deliveryPrice = deliveryPrice,
                    deliveryMethod = deliveryMethod,
                    onSelectDeliveryMethod = onDeliveryMethodChange
                )

                Spacer(modifier = Modifier.height(sectionSpacing))

                CheckoutSectionText(text = "Forma de pagamento")

                Spacer(modifier = Modifier.weight(1f))

                CheckoutSummary(
                    cartTotal = cartTotal,
                    deliveryPrice = if (deliveryMethod == DeliveryMethod.DELIVERY) deliveryPrice else 0.0,
                    totalSummary = orderTotal,
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
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = changeDeliveryAddress
    ) {

        DeliveryAddressList(
            addresses = deliveryAddresses,
            onSelectAddress = {
                onChangeDeliveryAddress(it)
                changeDeliveryAddress = false
            },
            onDismiss = { changeDeliveryAddress = false }
        )
    }
}
