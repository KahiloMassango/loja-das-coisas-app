package com.example.store.presentation.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.presentation.common.ThemePreviews
import com.example.store.ui.theme.StoreTheme

@Composable
internal fun ProfileOptions(
    modifier: Modifier = Modifier,
    onMyOrdersClick: () -> Unit,
    onMyReviewsClick: () -> Unit,
    onSettingsClick: () -> Unit

) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        ProfileOption(
            modifier = Modifier,
            option = "Minhas encomendas",
            description = "Já tem 12 encomendas",
            onClick = onMyOrdersClick
        )

        Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOption(
            modifier = Modifier,
            option = "Minhas avaliações",
            description = "Avaliações de 4 itens",
            onClick =  onMyReviewsClick
        )

        Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOption(
            modifier = Modifier,
            option = "Definições",
            description = "Notification, senha, etc",
            onClick = onSettingsClick
        )
    }
}

@Composable
private fun ProfileOption(
    modifier: Modifier = Modifier,
    option: String,
    description: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
          //  .minimumInteractiveComponentSize()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.Normal
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProfileOptions(
            onMyOrdersClick = {},
            onMyReviewsClick = {},
            onSettingsClick = {}
        )
    }
}
