package com.example.store.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.store.ui.theme.StoreTheme

@Composable
fun StoreTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    elevation: Dp = 2.dp,
    isError: Boolean = false,
    enabled: Boolean = true,
    errorMessage: String = "",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val textFieldBorderColor = if (isError) MaterialTheme.colorScheme.error
    else Color.Transparent

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation, MaterialTheme.shapes.small)
                .border(1.dp, textFieldBorderColor, MaterialTheme.shapes.small),
            value = value,
            textStyle = MaterialTheme.typography.bodyMedium,
            enabled = enabled,
            onValueChange = { onValueChange(it) },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                disabledIndicatorColor = Color.Unspecified,
                //disabledContainerColor = Color.Transparent,
            ),
            label = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isError) MaterialTheme.colorScheme.error else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        )
        if (isError) {
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        var value by remember {
            mutableStateOf("")
        }
        StoreTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = "Password",
            isError = false,
            errorMessage = "Error Message"
        )
    }
}