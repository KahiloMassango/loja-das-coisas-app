package com.example.store

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import com.example.store.core.notification.NotificationHelper
import com.google.firebase.messaging.FirebaseMessaging


@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            StoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    LaunchedEffect(key1 = null) {
                        FirebaseMessaging.getInstance().token.addOnCompleteListener {
                           Log.d("token", "Refreshed token: ${it.result}")
                       }
                    }
                    NavigationGraph()
                }
            }
        }
        requestNotificationPermission()
        NotificationHelper().createChannel(this)

    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        val notificationPermission = Manifest.permission.POST_NOTIFICATIONS
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }
        when {
            ContextCompat.checkSelfPermission(this, notificationPermission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, notificationPermission) -> {
                // Show rationale
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(notificationPermission)
            }
        }
    }


}
