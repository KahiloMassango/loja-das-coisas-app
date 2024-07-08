package com.example.store.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
        modifier = modifier

    ) {
        Surface(
            modifier = modifier.height(60.dp),
            shadowElevation = if (isError) 0.dp else elevation,
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.background,
            border = BorderStroke(1.dp, textFieldBorderColor)
        ) {
            Column(
                modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = value.isNotEmpty(),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 3.dp),
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isError) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    enabled = enabled,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.inverseOnSurface)
                                )
                            }
                            innerTextField()
                        }
                    },
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    visualTransformation = visualTransformation
                )

            }
        }
        if (isError) {
            Text(
                modifier = Modifier.padding(top = 2.dp, start = 8.dp),
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