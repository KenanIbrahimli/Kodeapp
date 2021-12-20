package com.knni.kode_app.api

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val error: Exception? = null): ResultWrapper<Nothing>()
}