package com.walletline.domain.model

sealed interface EmailValidationMessage {
    data class Dynamic(val message: String) : EmailValidationMessage
    object NotEmpty : EmailValidationMessage
    object NotValid : EmailValidationMessage
}