package com.example.store.core.data.repository

import com.example.store.core.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(email: String, password: String)
    suspend fun createAccount(email: String, password: String, phoneNumber: String, gender: String)
    suspend fun updateUserInfo(name: String, email: String, password: String, phoneNumber: String)
    suspend fun updateUserPassword(password: String)

    suspend fun saveUserJwtToken(token: String)

    suspend fun upsertLocalUser(user: User)
    suspend fun deleteLocalUser()
    suspend fun getLocalUserInfo(): Flow<User>



}