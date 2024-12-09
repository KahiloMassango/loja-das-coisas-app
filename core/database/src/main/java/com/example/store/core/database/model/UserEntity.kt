package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.user.User

@Entity("user")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val email: String,
    @ColumnInfo("phone_number")
    val phoneNumber: String,
    val gender: String,
    @ColumnInfo("image_url")
    val imageUrl: String?
)

fun UserEntity.asExternalModel() =  User(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    imageUrl = imageUrl,
    gender = gender
)