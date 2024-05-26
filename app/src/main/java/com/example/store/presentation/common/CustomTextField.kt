package com.example.store.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.store.ui.theme.StoreTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String = "",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
    val textFieldBorderColor = if(isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface

    Column(
        modifier = modifier
    ){
        TextField(
            modifier = Modifier
                .border(1.dp, textFieldBorderColor, MaterialTheme.shapes.medium)
                .fillMaxWidth(),
            value = value,
            onValueChange = { onValueChange(it) },
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge,
            label = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodySmall,
                    color = if(isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            },
            isError = isError,

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.scrim,
                unfocusedTextColor = MaterialTheme.colorScheme.scrim,
                errorTextColor = MaterialTheme.colorScheme.scrim,
                errorContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation
        )
        AnimatedVisibility(visible = isError) {
            Text(
                modifier = Modifier.padding(start = 14.dp, top = 4.dp),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        var value by remember {
            mutableStateOf("Input text")
        }
        CustomTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = "Password",
            isError = false,
            errorMessage = "Error Message"

        )
    }
}