package com.example.store.features.userprofile.orders

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.userprofile.orders.component.MyOrdersTabs
import com.example.store.features.userprofile.orders.component.OrdersContentList
import com.example.store.features.userprofile.orders.model.OrderTabs

@Composable
fun MyOrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: MyOrdersViewModel = hiltViewModel(),
    onDetailClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    var currentTab by rememberSaveable { mutableStateOf(OrderTabs.DELIVERED) }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            StoreLargeTopBar(
                title = "Minhas Encomendas",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(14.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(GenericShape { size, _ ->
                            lineTo(size.width, 0f)
                            lineTo(size.width, Float.MAX_VALUE)
                            lineTo(0f, Float.MAX_VALUE)
                        })
                        .shadow(4.dp)
                        .background(MaterialTheme.colorScheme.surface),

                    ) {
                    MyOrdersTabs(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        currentTab = currentTab,
                        onSelectTab = { currentTab = it },
                    )
                }
                AnimatedContent(
                    targetState = currentTab, label = "AnimatedContent"
                ) { tab ->
                    when (tab) {
                        OrderTabs.DELIVERED -> OrdersContentList(onDetailClick = onDetailClick)
                        OrderTabs.PROCESSING -> OrdersContentList(onDetailClick = onDetailClick)
                        OrderTabs.CANCELED -> OrdersContentList(onDetailClick = onDetailClick)
                    }
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        MyOrdersScreen(
            onDetailClick = {},
            onNavigateUp = {}
        )
    }
}