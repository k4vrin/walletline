package com.walletline.domain.model

sealed interface PatternValidationMessage {
    data class Dynamic(val message: String) : PatternValidationMessage
    object NotSame : PatternValidationMessage
}