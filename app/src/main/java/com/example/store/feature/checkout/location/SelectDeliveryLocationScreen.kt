package com.example.store.feature.checkout.location

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.feature.checkout.location.components.SelectDeliveryLocationContent
import com.example.store.feature.search.components.NoLocationPermissionScreen

@Composable
fun SelectDeliveryLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: DeliveryLocationViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResults.collectAsStateWithLifecycle()

    val requestPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("SelectDeliveryLocationScreen", "SelectDeliveryLocationScreen: $isGranted ")
            viewModel.getCurrentLocation()
        }
    }

    if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED
    ) {
        SelectDeliveryLocationContent(
            modifier = modifier,
            locationName = uiState.locationName,
            currentLocation = uiState.location,
            query = uiState.searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            searchResult = searchResult,
            onMoveToUserLocation = { viewModel.getCurrentLocation() },
            onLocationChange = { viewModel.selectLocation(it) },
            onConfirmLocation = { /* TODO */ },
            onNavigateUp = onNavigateUp
        )
    } else {
        NoLocationPermissionScreen(
            onGrant = { requestPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION) },
            onBack = onNavigateUp
        )

    }
}


