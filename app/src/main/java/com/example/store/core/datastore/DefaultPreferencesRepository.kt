package com.example.store.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.store.core.datastore.di.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DefaultPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {
    companion object PreferencesKey {
        val ENABLE_NOTIFICATIONS = booleanPreferencesKey("receive_notification")
    }


    override suspend fun updateNotificationPreference(enableNotifications: Boolean) {
        dataStore.edit { preferences ->
                preferences[ENABLE_NOTIFICATIONS] = enableNotifications

        }
    }

    override fun getNotificationPreference(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e("UserPreferencesRepository", "Error reading preferences", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[ENABLE_NOTIFICATIONS] ?: false
            }
    }
}