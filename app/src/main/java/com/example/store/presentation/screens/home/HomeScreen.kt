package com.example.store.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.screens.home.component.HomeBanner
import com.example.store.presentation.screens.home.component.Section
import com.example.store.ui.theme.StoreTheme

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeBanner()

            Spacer(modifier = Modifier.height(18.dp))

            HomeContent(Modifier)

            Spacer(modifier = Modifier.height(18.dp))

            /*Text(text = "Home")
            Button(onClick = { navController.navigate(Route.AuthGraph) }) {
                Text(text = "Auth Graph")
            }*/
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(44.dp)
    ) {
        Section(
            title = "Sale",
            description = "Super summer sale",
            items = 15,
            onItemClick = {},
            onViewAllClick = {}
        )
        Section(
            title = "New",
            description = "You’ve never seen it before!",
            items = 15,
            onItemClick = {},
            onViewAllClick = {}
        )

    }
}



@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        HomeScreen(navController = rememberNavController())
    }
}