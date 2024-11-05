package com.example.store.feature.reviews.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.CustomDragHandle
import com.example.store.core.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCommentSheet(
    modifier: Modifier = Modifier,
    state: SheetState,
    rate: Int = 0,
    comment: String,
    onRateChange: (Int) -> Unit,
    onCommentChange: (String) -> Unit,
    onSend: () -> Unit,
    onDismissRequest: () -> Unit
) {

    ModalBottomSheet(
        sheetState = state,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle("Qual é a sua avaliação")
        },
        contentWindowInsets = { WindowInsets.ime.union(WindowInsets.navigationBars) }
    ){
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (index in 1..5) {
                    val isSelected = index <= rate
                    val icon = if (isSelected) R.drawable.filled_star else R.drawable.outlined_star
                    IconButton(
                        onClick = { onRateChange(index) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(26.dp),
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = if (isSelected) Color(0xFFFFBA49) else
                                MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                }
            }
            Text(
                text = "Por favor, compartilhe sua opinião\n" +
                        "sobre o produto",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            CommentTextField(
                modifier = Modifier
                    .heightIn(150.dp)
                    .fillMaxWidth(),
                value = comment,
                onValueChange = { onCommentChange(it) },
            )
            CustomButton(
                text = "Enviar Opinião",
                onClick = onSend
            )

        }
    }
}

@Composable
private fun CommentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,

    ) {
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier
            .shadow(3.dp, MaterialTheme.shapes.small)
            .border(1.dp, Color.Transparent, MaterialTheme.shapes.small),
        value = value,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = { onValueChange(it) },
        singleLine = false,
        placeholder = {
            Text(
                text = "Sua opinião",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified,
            disabledIndicatorColor = Color.Unspecified,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    StoreTheme {
        AddCommentSheet(
            modifier = Modifier.fillMaxWidth(),
            comment = "",
            onCommentChange = {},
            onRateChange = {},
            state = SheetState(skipPartiallyExpanded = true, density = LocalDensity.current),
            onDismissRequest = {},
            onSend = {}
        )
    }
}
