package com.example.store.core.notification

import android.content.pm.PackageManager
import android.util.Log
import com.example.store.core.datastore.DefaultPreferencesRepository
import com.example.store.core.datastore.di.UserPreferencesRepository
import com.example.store.core.datastore.di.userDataStore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


class StoreFirebaseMessagingService(

): FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token) // Call super first to ensure proper token handling by FirebaseLog.d("NewToken", "Refreshed token: $token") // Improved log tag for clarity
        // TODO: Send the new token to your application server for future message targeting.
    }

}