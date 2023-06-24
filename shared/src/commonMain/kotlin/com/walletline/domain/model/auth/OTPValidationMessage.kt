package com.walletline.domain.model.auth

sealed interface OTPValidationMessage {
    object NotEmpty : OTPValidationMessage
    object NotValid : OTPValidationMessage
}