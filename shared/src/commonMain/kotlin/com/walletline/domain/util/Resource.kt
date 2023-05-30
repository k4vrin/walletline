package com.walletline.domain.util

sealed interface Resource<out SUCCESS, out ERROR> {
    class Success<T>(val data: T) : Resource<T, Nothing>
    class Error<E>(val data: E? = null, val message: String? = null) : Resource<Nothing, E>
}