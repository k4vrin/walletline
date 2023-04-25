package com.walletline.domain.model

data class RegisteredError(
    val emailError: List<String>? = null,
    val deviceError: List<String>? = null
)

data class RegisteredSuccess(
    val devCode: String? = null,
    val trackCode: String? = null,
)
