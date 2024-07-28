package com.example.store.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun Test(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "title",
                canNavigateBack = true,
                onNavigateUp = { }
            )
        },
        //contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Surface(
            //   modifier = modifier
            //   .zIndex(1f)
            //    .fillMaxWidth(),
            //  shadowElevation = 3.dp,
            //) {
            //   Box {
            SortingHeader1(
                modifier = Modifier
                    //.padding(start = 16.dp, bottom = 8.dp)
                    //.zIndex(1f)
                    .fillMaxWidth()
                    .shadow(3.dp),
                onSortClick = { }
            )
            // }
            //}
        }
    }
}

@Composable
fun SortingHeader1(
    modifier: Modifier = Modifier,
    onSortClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(GenericShape { size, _ ->
                lineTo(size.width, 0f)
                lineTo(size.width, Float.MAX_VALUE)
                lineTo(0f, Float.MAX_VALUE)
            })
            .shadow(3.dp)
            .background(MaterialTheme.colorScheme.surface),

    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .clickable { onSortClick() },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(id = R.drawable.sort_icon),
                contentDescription = "Sort",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Ordenar por",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        Test()
    }
}
