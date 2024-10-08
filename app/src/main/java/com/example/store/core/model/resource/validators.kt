package com.example.store.core.model.resource

import android.util.Patterns
import androidx.core.text.isDigitsOnly

fun isPasswordValid(password: String): Boolean {
    return password.length >= 8
}


fun isNameValid(name: String): Boolean {
    val regex = Regex("^[a-zA-Z ]*\$")
    return name.length >= 4 && regex.matches(name)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith(".com")
}

fun isPhoneNumberValid(phoneNumber: String): Boolean {
    val n = phoneNumber.length
    return n == 9 && phoneNumber.isDigitsOnly()
}


fun isConfirmPasswordValid(confirmPassword: String, password: String): Boolean {
    return confirmPassword == password
}
