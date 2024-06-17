package com.example.store.presentation.screens.my_orders

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.component.StoreLargeTopBar
import com.example.store.presentation.screens.my_orders.component.MyOrdersTabs
import com.example.store.presentation.screens.my_orders.component.OrderItemCard
import com.example.store.presentation.screens.my_orders.model.OrderTabs
import com.example.store.ui.theme.StoreTheme

@Composable
fun MyOrdersScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var currentTab by rememberSaveable { mutableStateOf(OrderTabs.DELIVERED) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StoreLargeTopBar(
                title = "Minhas Encomendas",
                canNavigateBack = true,
                onNavigateUp = navController::navigateUp
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
        ){
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                MyOrdersTabs(
                    modifier = Modifier.fillMaxWidth(),
                    currentTab = currentTab,
                    onSelectTab = { currentTab = it },
                )
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedContent(
                    targetState = currentTab, label = "AnimatedContent"
                ) { tab ->
                    when(tab) {
                        OrderTabs.DELIVERED -> OrdersContentList{}
                        OrderTabs.PROCESSING -> OrdersContentList{}
                        OrderTabs.CANCELED -> OrdersContentList{}
                    }
                }
            }
        }
    }
}


@Composable
private fun OrdersContentList(
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(14) {
            item { OrderItemCard(
                modifier = Modifier.padding(bottom = 24.dp),
                onDetailClick = onDetailClick
            )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        MyOrdersScreen(navController = rememberNavController())
    }
}