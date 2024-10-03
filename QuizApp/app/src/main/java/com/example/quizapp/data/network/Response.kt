package com.example.quizapp.data.network

sealed class Response<out R> {
    data class Success<out T>(val data: T?) : Response<T>()
    data class Error(val exception: Throwable) : Response<Nothing>()
    object Loading : Response<Nothing>()
}