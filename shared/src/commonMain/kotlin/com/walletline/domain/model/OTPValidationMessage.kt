package com.walletline.domain.model

sealed interface OTPValidationMessage {
    data class Dynamic(val message: String) : OTPValidationMessage
    object NotEmpty : OTPValidationMessage
    object NotValid : OTPValidationMessage
}