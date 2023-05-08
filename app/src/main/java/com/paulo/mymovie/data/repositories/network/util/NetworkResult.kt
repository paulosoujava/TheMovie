package com.paulo.mymovie.data.repositories.network.util

sealed class NetworkResult<T: Any> {
     class Success<T: Any>(val data: T) : NetworkResult<T>()
     class Error<T: Any>(val code: Int, val message: String) : NetworkResult<T>()
    class Exception<T: Any>(val exception: Throwable) : NetworkResult<T>()
}