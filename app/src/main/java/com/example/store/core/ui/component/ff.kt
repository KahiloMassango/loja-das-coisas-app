package com.example.store.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.core.data.mock.p1
import com.example.store.core.model.AddressType
import com.example.store.core.model.Product
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun ProductCard1(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, bottom = 14.dp, top = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.2f),
                            RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp),
                        painter = painterResource(id = AddressType.HOME.icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = AddressType.HOME.description,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier =  Modifier
                                .background(MaterialTheme.colorScheme.outlineVariant.copy(0.7f),RoundedCornerShape(25))
                                .clip(RoundedCornerShape(10))
                        ) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "Padrão",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                    Text(
                        text = "AddressType.HOME.descriptionfsgsjjjkktyry",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            IconButton(
                modifier = Modifier,
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        ProductCard1(
            onClick = {},
            product = p1,
        )
    }
}