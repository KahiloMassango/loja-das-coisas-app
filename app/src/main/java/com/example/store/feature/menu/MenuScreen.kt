 package com.example.store.feature.menu

aimport androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.menu.component.MenuOptions
import com.example.store.feature.menu.component.UserInfoCard

 @Composable
fun MenuScreen(
     modifier: Modifier = Modifier,
     onMyOrdersClick: () -> Unit,
     onEditProfileClick: () -> Unit,
     onChangePasswordClick: () -> Unit,
     onHelpCenterClick: () -> Unit,
     onAddressesClick: () -> Unit,
     onPolicePrivacyClick: () -> Unit
) {
    Scaffold(
        topBar = {
            StoreLargeTopBar(title = "Menu", canNavigateBack = false )
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                UserInfoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    userName = "Matilda Brown",
                    userEmail = "matildabrown@mail.com",
                    onLogout = {}
                )
                Spacer(modifier = Modifier.height(46.dp))
                MenuOptions(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f),
                    onMyOrdersClick = onMyOrdersClick,
                    onEditProfileClick = onEditProfileClick,
                    onChangePasswordClick = onChangePasswordClick,
                    onHelpCenterClick= onHelpCenterClick,
                    onPolicePrivacyClick = onPolicePrivacyClick,
                    onAddressesClick = onAddressesClick
                )
            }
        }
    }
}




@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        MenuScreen(
            onMyOrdersClick = {},
            onEditProfileClick = {},
            onChangePasswordClick = {},
            onHelpCenterClick = {},
            onAddressesClick = {},
            onPolicePrivacyClick = {}
        )
    }
}
