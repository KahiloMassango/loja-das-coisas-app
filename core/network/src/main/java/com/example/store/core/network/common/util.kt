package com.example.store.core.network.common

import com.example.store.core.network.model.Response
import com.google.gson.Gson
import retrofit2.HttpException

internal fun extractErrorMessage(exception: HttpException): String {
    val errorBody = exception.response()?.errorBody()!!.charStream()
    val errorMessage = Gson().fromJson(errorBody, Response::class.java)

    return errorMessage.message
}