package com.example.store.features.discover

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.SearchField
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.discover.component.GenderContainer
import com.example.store.features.discover.model.Gender

@Composable
internal fun CategoryScreen(
    modifier: Modifier = Modifier,
    onSelectCategory: (gender: String, category: String) -> Unit,
    onSearch: () -> Unit,
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        Spacer(Modifier.height(32.dp))
        SearchField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable(null, null) { onSearch() },
            query = "",
            placeholder = "Pesquise por lojas, roupas, calçados, acessórios, etc.",
            enabled = false,
            onQueryChange = {},
            onSearch = {},
            onClearQuery = {},
        )
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Explorar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Gender.entries.forEach {
                GenderContainer(
                    gender = it,
                    onCategoryClick = { gender, category ->
                        onSelectCategory(gender, category)
                    }
                )
            }
        }
    }
}


@Preview(
    name = "Compact",
    device = "spec:width=411dp, height=891dp, dpi=420",
    showBackground = true
)
@Preview(name = "Medium", device = "spec:width=673dp,height=841dp,dpi=420", showBackground = true)
@Preview(
    name = "Expanded",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showBackground = true
)
@Composable
private fun Preview1() {
    StoreTheme {
        CategoryScreen(
            onSelectCategory = { _, _ -> },
            onSearch = {}
        )
    }
}