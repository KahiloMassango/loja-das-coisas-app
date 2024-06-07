package com.example.store.presentation.screens.shop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.presentation.component.CustomDragHandle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortingOptions(
    state: SheetState,
    selectedOption: Int,
    onDismissRequest: () -> Unit,
    onOptionClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        modifier = modifier.navigationBarsPadding(),
        sheetState = state,
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismissRequest,
        windowInsets = WindowInsets(0),
        dragHandle = {
            CustomDragHandle("Ordenar por")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(5) {
                OrderByOption(
                    selected = it == selectedOption,
                    onClick = {
                        onOptionClick(it)
                    }
                )
            }
        }
    }
}

@Composable
fun OrderByOption(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
   Box(
       modifier = modifier
           .background(if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground)
           .fillMaxWidth()
           .clickable { onClick() },
   ) {
       Text(
           modifier = Modifier.padding(16.dp),
           text = "Popular",
           style = MaterialTheme.typography.bodyLarge,
           color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.background
       )
   }
}