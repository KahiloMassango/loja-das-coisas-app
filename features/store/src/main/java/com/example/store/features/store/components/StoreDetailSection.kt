package com.example.store.features.store.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.R
import com.example.store.features.store.ADDRESS_1

@Composable
internal fun StoreDetailSection(
    modifier: Modifier = Modifier,
    onSeeOnMapClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Detalhes",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for" +
                    " those interested. Sections 1.10.32 and 1.10.33 from " +
                    "\"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their " +
                    "exact original form, accompanied by English versions from the 1914 translation " +
                    "by H. Rackham.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            softWrap = true,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(22.dp))
        StoreDetailItem(
            title = "Hórario de Funcionamento",
            detail = "08:00 - 17:30",
            icon = R.drawable.ic_time
        )
        Spacer(Modifier.height(18.dp))
        StoreDetailItem(
            title = "Membro desde",
            detail = "Junho 2022",
            icon = R.drawable.ic_user
        )
        Spacer(Modifier.height(18.dp))
        StoreDetailItem(
            title = "NIF",
            detail = "008649092LA048",
            icon = R.drawable.ic_user_admin
        )
        Spacer(Modifier.height(18.dp))
        StoreAddressDetailItem(
            title = "Localização",
            address = ADDRESS_1,
            onSeeOnMapClick = onSeeOnMapClick
        )
    }
}