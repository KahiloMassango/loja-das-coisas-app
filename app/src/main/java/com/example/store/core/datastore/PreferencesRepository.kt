package com.example.store.core.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun updateNotificationPreference(enableNotifications: Boolean)
    fun getNotificationPreference(): Flow<Boolean>

    suspend fun setFirstTimeCompleted(isFirstTime: Boolean)
    fun isFirstTimeCompleted(): Flow<Boolean>


}