package com.example.store.features.productdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.product.ProductItem
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.R
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.productdetail.component.AddToCartContainer
import com.example.store.features.productdetail.component.AttributeSelector
import com.example.store.features.productdetail.component.ProductAttributes
import com.example.store.features.productdetail.component.ProductDetails
import com.example.store.features.productdetail.component.ProductImageCarousel


@Composable
internal fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    onStoreClick: (String) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onReviewsClick: (String) -> Unit,
    onSuggestedProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    val selectedColor by viewModel.selectedColor.collectAsStateWithLifecycle()
    val selectedSize by viewModel.selectedSize.collectAsStateWithLifecycle()

    val filteredColors by viewModel.filteredColors.collectAsStateWithLifecycle()
    val filteredSizes by viewModel.filteredSizes.collectAsStateWithLifecycle()

    // Display the selected combination's price and stock
    val selectedItem by viewModel.selectedItem.collectAsStateWithLifecycle()

    val productImages = viewModel.productImages.value




    when (uiState) {
        is ProductDetailState.Loading -> LoadingScreen(modifier)
        is ProductDetailState.Error -> ErrorScreen(onTryAgain = viewModel::refresh)
        is ProductDetailState.Success -> ProductDetailContent(
            modifier = modifier,
            product = uiState.product,
            productImages = productImages,
            isFavorite = isFavorite,
            onStoreClick = onStoreClick,
            onNavigateUp = onNavigateUp,
            onReviewsClick = { onReviewsClick(it) },
            onAddToCart = viewModel::addCart,
            onAddFavorite = {},
            selectedItem = selectedItem,
            selectedColor = selectedColor,
            selectedSize = selectedSize,
            filteredColors = filteredColors,
            filteredSizes = filteredSizes,
            onSelectColor = viewModel::selectColor,
            onSelectSize = viewModel::selectSize,
        )
    }

}

@Composable
private fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: ProductWithVariation,
    productImages: List<String>,
    isFavorite: Boolean,
    onAddFavorite: () -> Unit,
    onReviewsClick: (String) -> Unit,
    onAddToCart: () -> Unit,
    onStoreClick: (String) -> Unit,
    selectedItem: ProductItem?,
    selectedColor: String?,
    selectedSize: String?,
    filteredColors: List<String>,
    filteredSizes: List<String>,
    onSelectColor: (String) -> Unit,
    onSelectSize: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    var showColorSelector by remember { mutableStateOf(false) }
    var showSizeSelector by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                modifier = Modifier,
                title = product.title,
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
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(),
                ) {

                    ProductImageCarousel(images = productImages)

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            //.weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        ProductAttributes(
                            modifier = Modifier,
                            subcategory = product.subCategory,
                            selectedSize = selectedSize,
                            selectedColor = selectedColor,
                            onShowSizeOptions = { showSizeSelector = true },
                            onShowColorOptions = { showColorSelector = true }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        ProductDetails(
                            modifier = Modifier,
                            onStoreClick = { onStoreClick(product.storeId) },
                            name = product.title,
                            description = product.description,
                            storeName = product.storeName,
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { onReviewsClick(product.id) }
                        ) {
                            Text(
                                text = "Reviews"
                            )
                        }
                    }

                    /*item {
                        ReviewAndRatingSection(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            ratingInfo = ratingInfosList.first()
                        )
                    }*/

                    //item { Spacer(modifier = Modifier.height(14.dp)) }
                    /*items(ratingsList.filterNot { it.comment == null }.take(2)) { rating ->
                        ReviewCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            rating = rating
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                    }*/
                }
                AddToCartContainer(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .align(Alignment.BottomCenter),
                    price = selectedItem?.price ?: 0.0,
                    onAddToCart = onAddToCart
                )
            }
        }
        if (showSizeSelector) {
            AttributeSelector(
                label = "Selecione tamanho",
                selectedOption = selectedSize,
                options = filteredSizes.map { it.uppercase() },
                onSelectOption = {
                    onSelectSize(it)
                },
                onDismissRequest = { showSizeSelector = false }
            )
        }
        if (showColorSelector) {
            AttributeSelector(
                label = "Selecione cor",
                selectedOption = selectedColor,
                options = filteredColors,
                onSelectOption = {
                    onSelectColor(it)
                },
                onDismissRequest = { showColorSelector = false }
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        /* ProductDetailContent(
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
         )*/
    }
}