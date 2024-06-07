package com.example.store.presentation.screens.autentication.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.ui.theme.StoreTheme

@Composable
fun CustomClickableText(
    modifier: Modifier = Modifier,
    text: String,
    supportText: String? = null,
    textArrangement: Arrangement.Horizontal = Arrangement.End,
    onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)
        ){
            append(text)
        }
        append(" ")
        if(supportText != null) {
            withStyle(
                style = SpanStyle(color = MaterialTheme.colorScheme.primary)
            ) {
                append(supportText)
            }
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = textArrangement
    ) {
        BasicText(
            text = annotatedString,
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(end = 4.dp),
            style = MaterialTheme.typography.labelMedium,
        )
        if(supportText == null) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StoreTheme {
        CustomClickableText(
            text = "Forgot your password?",
            onClick = {  }
        )
    }
}