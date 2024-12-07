package com.example.store.features.store

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.store.components.StoreDetailSection
import com.example.store.features.store.components.StoreHeader
import com.example.store.features.store.components.StoreMapDialog
import com.example.store.features.store.components.StoreProductsListingGrid
import com.example.store.features.store.components.StoreSection

val ADDRESS_1 = Address(
    1,
    "test1",
    "123456789",
    AddressType.HOME,
    AddressLine("Samba, Luanda", "street 1, Gamek, Luanda"),
    - 8.893235, 13.205504
)

@Composable
internal fun StoreScreen(
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {

    var currentSection by rememberSaveable { mutableStateOf(StoreSection.AllProducts) }
    var showMapDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Loja",
                onNavigateUp = onNavigateUp,
                elevation = 5.dp
            )
        }

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxSize()
            ) {
                StoreHeader(
                    modifier = Modifier.fillMaxWidth(),
                    currentSection = currentSection,
                    onChangeSection = { currentSection = it }
                )

                Spacer(Modifier.height(16.dp))

                AnimatedContent(
                    targetState = currentSection,
                    modifier = Modifier.weight(1f), label = ""
                ) { section ->
                    when (section) {
                        StoreSection.AllProducts -> {
                            StoreProductsListingGrid(
                                description = section.description,
                                products = emptyList(),
                                onProductClick = onProductClick
                            )
                        }

                        StoreSection.About -> {
                            StoreDetailSection(
                                onSeeOnMapClick = { showMapDialog = true }
                            )
                        }
                    }
                }
            }
        }
    }
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = showMapDialog
    ) {
        StoreMapDialog(
            modifier = Modifier,
            address = ADDRESS_1,
            onDismiss = { showMapDialog = false }
        )

    }
}





@Preview
@Composable
private fun Preview() {
    StoreTheme {
        StoreScreen(
            onProductClick = {},
            onNavigateUp = { }
        )
    }
}