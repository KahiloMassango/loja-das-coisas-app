package com.example.store.feature.product_detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.R
import com.example.store.core.data.mock.productList
import com.example.store.core.model.Product
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.product_detail.component.AttributePickerSheet
import com.example.store.feature.product_detail.component.ProductAttributeSection
import com.example.store.feature.product_detail.component.ProductDetailsSection
import com.example.store.feature.product_detail.component.ProductImageCarousel
import com.example.store.feature.product_detail.component.RelatedProductsSection
import com.example.store.presentation.component.CustomButton
import com.example.store.presentation.component.FavoriteButton
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.component.ThemePreviews
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val images = listOf(R.drawable.detail_image_ex1, R.drawable.detail_image_ex2)

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onReviewsClick: (String) -> Unit,
    onSuggestedProductsClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value


    when (uiState) {
        is ProductDetailState.Loading -> LoadingScreen(modifier)
        is ProductDetailState.Error -> LoadingScreen()
        is ProductDetailState.Success -> ProductDetailContent(
            modifier = modifier,
            product = uiState.product,
            size = uiState.size,
            color = uiState.color,
            onSizeChange = { viewModel.updateSize(it) },
            onColorChange = { viewModel.updateColor(it) },
            onNavigateUp = onNavigateUp,
            onReviewsClick = { onReviewsClick(it) },
            onSuggestedProductsClick = { onSuggestedProductsClick(it) },
            onAddToCart = viewModel::addCart
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: Product,
    size: String,
    color: String,
    onSizeChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onNavigateUp: () -> Unit,
    onReviewsClick: (String) -> Unit,
    onAddToCart: () -> Unit,
    onSuggestedProductsClick: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val sizeOptionsState = rememberModalBottomSheetState()
    var showSizeOptions by remember { mutableStateOf(false) }

    val colorOptionsState = rememberModalBottomSheetState()
    var showColorOptions by remember { mutableStateOf(false) }

    if (showSizeOptions) {
        AttributePickerSheet(
            state = sizeOptionsState,
            selectorDescription = "Selecionar tamanho",
            selectedAttribute = size,
            attributes = product.availableSizes,
            onDismissRequest = { showSizeOptions = false },
            onSelectAttribute = {
                onSizeChange(it)
                coroutineScope.launch {
                    delay(350)
                    sizeOptionsState.hide()
                }.invokeOnCompletion {
                    showSizeOptions = false
                }
            }
        )
    }
    if (showColorOptions) {
        AttributePickerSheet(
            state = colorOptionsState,
            selectorDescription = "Selecionar cor",
            selectedAttribute = color,
            attributes = product.availableColors,
            onDismissRequest = { showColorOptions = false },
            onSelectAttribute = {
                onColorChange(it)
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
                modifier = Modifier.shadow(3.dp),
                title = product.name,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
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
                    .verticalScroll(rememberScrollState(2000)),
            ) {
                ProductImageCarousel(images = images)
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
                        ProductAttributeSection(
                            modifier = Modifier.weight(1f),
                            selectedSize = size,
                            selectedColor = color,
                            showSizeOptions = { showSizeOptions = true },
                            showColorOptions = { showColorOptions = true }
                        )
                        FavoriteButton(
                            modifier = Modifier,
                            isFavorite = false,
                            onClick = { /*TODO*/ }
                        )
                    }
                    Spacer(modifier = Modifier.height(22.dp))
                    ProductDetailsSection(
                        productName = product.name,
                        storeName = product.storeName,
                        averageRating = product.averageRating,
                        totalRating = product.totalRating,
                        price = "${product.price}Kz",
                        description = product.description
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "ADICIONAR AO CARRINHO",
                        onClick = onAddToCart
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                }
                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onReviewsClick(product.id) }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Comentários & Avaliações",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                RelatedProductsSection(
                    modifier = Modifier.padding(16.dp),
                    onProductClick = { onSuggestedProductsClick(it) },
                    onFavoriteClick = { /* TODO */ },
                    products = productList
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductDetailsScreen(
            onReviewsClick = {},
            onNavigateUp = {},
            onSuggestedProductsClick = {}
        )
    }
}