package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = 0")
    fun getUser(): Flow<UserEntity>

    @Query("DELETE FROM user WHERE id = 0")
    suspend fun deleteUser()

}