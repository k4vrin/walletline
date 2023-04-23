package com.walletline.domain.util

data class ValidationResult<T>(
    val isSuccess: Boolean,
    val message: T? = null
)
