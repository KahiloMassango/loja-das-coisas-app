package com.example.store.presentation.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.R
import com.example.store.presentation.common.CustomButton
import com.example.store.presentation.common.FavoriteButton
import com.example.store.presentation.common.StoreCenteredTopBar
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.screens.checkout.components.ColorSelector
import com.example.store.presentation.screens.checkout.components.SizeSelector
import com.example.store.presentation.screens.detail.components.ProductDetailsSection
import com.example.store.presentation.screens.detail.components.ProductImageSlider
import com.example.store.presentation.screens.detail.components.RelatedProductsSection
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val images = listOf(R.drawable.detail_image_ex1, R.drawable.detail_image_ex2)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    val availableSizes = listOf("S", "M", "L", "XL", "XS")
    val sizeOptionsState = rememberModalBottomSheetState()
    var showSizeOptions by remember { mutableStateOf(false) }
    var selectedSize by rememberSaveable { mutableStateOf(availableSizes[0]) }

    val availableColors = listOf("Azul", "Vermelho", "Verde", "Amarelo","Laranja", "Roxo", "Rosa", "Cinza", "Preto")
    val colorOptionsState = rememberModalBottomSheetState()
    var showColorOptions by remember { mutableStateOf(false) }
    var selectedColor by rememberSaveable { mutableStateOf(availableColors[0]) }

    val isFavorite = false

    if(showSizeOptions) {
        SizeSelector(
            state = sizeOptionsState,
            selectedSize = selectedSize,
            sizeOptions = availableSizes,
            onDismissRequest = { showSizeOptions = false },
            onSelectSize = { size ->
                selectedSize = size
                coroutineScope.launch {
                    delay(350)
                    sizeOptionsState.hide()
                }.invokeOnCompletion {
                    showSizeOptions = false
                }
            }
        )
    }
    if(showColorOptions) {
        ColorSelector(
            state = colorOptionsState,
            selectedColor = selectedColor,
            colors = availableColors,
            onDismissRequest = { showColorOptions = false },
            onSelectColor = { color ->
                selectedColor = color
                coroutineScope.launch {
                    delay(350)
                    colorOptionsState.hide()
                }.invokeOnCompletion {
                    showColorOptions = false
                }
            }
        )
    }

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = "M&H",
                canNavigateBack = true,
                onNavigateUp = navController::navigateUp
                )
        },
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                ProductImageSlider(images = images)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AttributeSelector(
                            modifier = Modifier.weight(1f),
                            attribute = "Tamanho",
                            selectedAttribute = selectedSize,
                            onClick = { showSizeOptions = true }
                        )
                        AttributeSelector(
                            modifier = Modifier.weight(1f),
                            attribute = "Cor",
                            selectedAttribute = selectedColor,
                            onClick = { showColorOptions = true }
                        )
                        FavoriteButton(
                            modifier = Modifier,
                            isFavorite = isFavorite,
                            onClick = { /*TODO*/ }
                        )
                    }
                    Spacer(modifier = Modifier.height(22.dp))
                    ProductDetailsSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "ADICIONAR AO CARRINHO",
                        onClick = { /* TODO: Add to cart */ }
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                    RelatedProductsSection()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AttributeSelector(
    attribute: String,
    selectedAttribute: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedCard(
        modifier = modifier ,
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$attribute: $selectedAttribute",
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Show size options" // Add content description
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        DetailScreen(rememberNavController())
    }
}