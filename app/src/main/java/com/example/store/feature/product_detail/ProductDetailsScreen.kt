package com.example.store.feature.product_detail

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.R
import com.example.store.core.data.mock.productList
import com.example.store.core.model.Product
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.product_detail.component.CartAndFavoriteBottomSheet
import com.example.store.feature.product_detail.component.ProductDetails
import com.example.store.feature.product_detail.component.ProductImageCarousel
import com.example.store.feature.product_detail.component.RelatedProductsSection
import com.example.store.feature.product_detail.component.SizeSelector

val images = listOf(R.drawable.detail_image_ex1, R.drawable.detail_image_ex2)

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    onStoreClick: (String) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onReviewsClick: (String) -> Unit,
    onSuggestedProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    when (uiState) {
        is ProductDetailState.Loading -> LoadingScreen(modifier)
        is ProductDetailState.Error -> ErrorScreen(onTryAgain = viewModel::refresh)
        is ProductDetailState.Success -> ProductDetailContent(
            modifier = modifier,
            product = uiState.product,
            size = viewModel.productSize,
            color = viewModel.productColor,
            isFavorite = isFavorite,
            onSizeChange = { viewModel.updateSize(it) },
            onColorChange = { viewModel.updateColor(it) },
            onStoreClick = onStoreClick,
            onNavigateUp = onNavigateUp,
            onReviewsClick = { onReviewsClick(it) },
            onSuggestedProductsClick = { onSuggestedProductClick(it) },
            onAddToCart = viewModel::addCart,
            onAddFavorite = viewModel::addFavorite
        )
    }

}

@Composable
private fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: Product,
    size: String,
    color: String,
    isFavorite: Boolean,
    onSizeChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onAddFavorite: () -> Unit,
    onReviewsClick: (String) -> Unit,
    onAddToCart: () -> Unit,
    onSuggestedProductsClick: (String) -> Unit,
    onStoreClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                modifier = Modifier,
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
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 0.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    ProductImageCarousel(images = images)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        ProductDetails(
                            onStoreClick = onStoreClick,
                            product = product
                        )
                        HorizontalDivider(Modifier.padding(vertical = 16.dp))
                        SizeSelector(
                            modifier = Modifier.fillMaxWidth(),
                            selectedSize = size,
                            availableSizes = product.availableSizes,
                            onChangeSize = { onSizeChange(it) }
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
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
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    RelatedProductsSection(
                        modifier = Modifier.padding(16.dp),
                        onProductClick = { onSuggestedProductsClick(it) },
                        onFavoriteClick = { /* TODO */ },
                        products = productList
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
                CartAndFavoriteBottomSheet(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),//.shadow(12.dp),
                    productPrice = product.price,
                    isFavorite = isFavorite,
                    onAddFavorite = onAddFavorite,
                    onAddToCart = {
                        onAddToCart()
                        Toast.makeText(context, "Adicionado ao carrinho", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductDetailContent(
            product = productList[0],
            size = "M",
            color = "Azul",
            isFavorite = false,
            onSizeChange = {},
            onColorChange = {},
            onAddFavorite = {},
            onReviewsClick = {},
            onAddToCart = {},
            onSuggestedProductsClick = {},
            onStoreClick = {},
            onNavigateUp = {}
        )
    }
}