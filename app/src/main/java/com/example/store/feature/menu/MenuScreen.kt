 package com.example.store.feature.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.menu.component.MenuOptions

 @Composable
fun MenuScreen(
     modifier: Modifier = Modifier,
     onMyOrdersClick: () -> Unit,
     onProfileClick: () -> Unit,
     onSettingsClick: () -> Unit,
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
                ProfileUserCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    name = "Matilda Brown",
                    email = "matildabrown@mail.com"
                )
                Spacer(modifier = Modifier.height(46.dp))
                MenuOptions(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f),
                    onMyOrdersClick = onMyOrdersClick,
                    onProfileClick = onProfileClick,
                    onSettingsClick = onSettingsClick,
                    onHelpCenterClick= onHelpCenterClick,
                    onPolicePrivacyClick = onPolicePrivacyClick,
                    onAddressesClick = onAddressesClick
                )
            }
        }
    }
}


@Composable
private fun ProfileUserCard(
    modifier: Modifier = Modifier,
    name: String,
    email: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Image(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.dog_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier,
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = email,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.inverseOnSurface,
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {

    }
}
