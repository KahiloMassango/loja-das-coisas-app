package com.example.store.core.datastore.di

import kotlinx.coroutines.flow.Flow


interface UserPreferencesRepository {

    suspend fun updateNotificationPreference(enableNotifications: Boolean)

    fun getNotificationPreference(): Flow<Boolean>
}