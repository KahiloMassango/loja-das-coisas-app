package com.example.store.feature.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.core.os.persistableBundleOf
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun ProfileOptions(
    modifier: Modifier = Modifier,
    onMyOrdersClick: () -> Unit,
    onMyReviewsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit

) {
    Column(
        modifier = modifier
    ) {
        ProfileOption(
            modifier = Modifier,
            option = "Minhas encomendas",
            description = "Já tem 12 encomendas",
            onClick = onMyOrdersClick
        )

        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOption(
            modifier = Modifier,
            option = "Minhas avaliações",
            description = "Avaliações de 4 itens",
            onClick =  onMyReviewsClick
        )

        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOption(
            modifier = Modifier,
            option = "Definições",
            description = "Notification, senha, etc",
            onClick = onSettingsClick
        )
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)
        ProfileOption(
            modifier = Modifier,
            option = "Centro de ajuda",
            description = "Encontre respostas para suas dúvidas.",
            onClick = onHelpCenterClick
        )
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)
        ProfileOption(
            modifier = Modifier,
            option = "Política de privacidade",
            description = "Saiba como seus dados são usados.",
            onClick = onPolicePrivacyClick
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.Normal
                )
            }
            Icon(
                modifier = Modifier.size(18.dp),
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
            onSettingsClick = {},
            onHelpCenterClick = {},
            onPolicePrivacyClick = {}
        )
    }
}
