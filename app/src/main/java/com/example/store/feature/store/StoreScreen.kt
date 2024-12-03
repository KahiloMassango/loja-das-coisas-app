package com.example.store.feature.store

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.store.components.StoreDetailItem
import com.example.store.feature.store.components.StoreHeader
import com.example.store.feature.store.components.StoreMapDialog
import com.example.store.feature.store.components.StoreProductsListingGrid
import com.example.store.feature.store.components.StoreSection

val ADDRESS_1 = Address(
    1,
    "test1",
    "123456789",
    AddressType.HOME,
    AddressLine("Samba, Luanda", "street 1, Gamek, Luanda"),
    - 8.893235, 13.205504
)

@Composable
fun StoreScreen(
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
                            StoreAboutSection(
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

@Composable
fun StoreAboutSection(
    modifier: Modifier = Modifier,
    onSeeOnMapClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Detalhes",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for" +
                    " those interested. Sections 1.10.32 and 1.10.33 from " +
                    "\"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their " +
                    "exact original form, accompanied by English versions from the 1914 translation " +
                    "by H. Rackham.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            softWrap = true,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(22.dp))
        StoreDetailItem(
            title = "Hórario de Funcionamento",
            detail = "08:00 - 17:30",
            icon = R.drawable.ic_time
        )
        Spacer(Modifier.height(18.dp))
        StoreDetailItem(
            title = "Membro desde",
            detail = "Junho 2022",
            icon = R.drawable.ic_user
        )
        Spacer(Modifier.height(18.dp))
        StoreDetailItem(
            title = "NIF",
            detail = "008649092LA048",
            icon = R.drawable.ic_user_admin
        )
        Spacer(Modifier.height(18.dp))
        StoreAddressDetailItem(
            title = "Localização",
            address = ADDRESS_1,
            onSeeOnMapClick = onSeeOnMapClick
        )
    }
}

@Composable
fun StoreAddressDetailItem(
    modifier: Modifier = Modifier,
    title: String,
    address: Address,
    onSeeOnMapClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            painter = painterResource(R.drawable.ic_map_pin),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Text(
                    text = address.addressLine.shortName,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null
                ) { onSeeOnMapClick() },
                text = "Ver no mapa",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
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