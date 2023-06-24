package com.walletline.domain.model.auth

sealed interface PatternValidationMessage {
    data class Dynamic(val message: String) : PatternValidationMessage
    object NotSame : PatternValidationMessage
}