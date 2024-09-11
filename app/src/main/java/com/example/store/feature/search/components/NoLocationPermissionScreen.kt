package com.example.store.feature.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun NoLocationPermissionScreen(
    modifier: Modifier = Modifier,
    onGrant: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Location Permission"
        )
        Button(onClick = onGrant) {
            Text(text = "Grant")
        }
        Button(onClick = onBack) {
            Text(text = "go back")
        }
    }
}