package com.example.store.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.data.mock.productList
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.home.component.HomeBanner
import com.example.store.presentation.screens.home.component.Section

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onSeeAll: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when(uiState) {
        is HomeUiState.Error -> LoadingScreen()
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(
            modifier = modifier,
            onProductClick = onProductClick,
            onSeeAll = onSeeAll
        )
    }

}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    onSeeAll: (String) -> Unit
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeBanner()
            Spacer(modifier = Modifier.height(18.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, ),
                verticalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                Section(
                    title = "Sale",
                    description = "Super summer sale",
                    products = productList,
                    onProductClick = { onProductClick(it) },
                    onSeeAll = { onSeeAll(it) },
                    onFavoriteClick = { /* TODO */ }
                )

                Section(
                    title = "New",
                    description = "You’ve never seen it before!",
                    products = productList,
                    onProductClick = { onProductClick(it) },
                    onSeeAll = { onSeeAll(it) },
                    onFavoriteClick = { /* TODO */ }
                )
            }
            Spacer(modifier = Modifier.height(18.dp))


            /*Text(text = "Home")
            Button(onClick = { navController.navigate(Route.AuthGraph) }) {
                Text(text = "Auth Graph")
            }*/
        }
    }
}



@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        HomeScreen(onProductClick = {}, onSeeAll = {})
    }
}