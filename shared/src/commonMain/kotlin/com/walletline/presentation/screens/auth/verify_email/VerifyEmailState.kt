package com.walletline.presentation.screens.auth.verify_email

import com.walletline.domain.model.auth.OTPValidationMessage

data class VerifyEmailState(
    val otp: String = "",
    val otpError: OTPValidationMessage? = null,
    val email: String = "",
    val timer: Int = 120,
    val isTimerActive: Boolean = true,
    val isLoading: Boolean = false,
    val isActionsEnabled: Boolean = true,
) {
    constructor() : this(
        otp = "",
        otpError = null,
        email = "",
        timer = 120,
        isTimerActive = true,
        isLoading = false,
        isActionsEnabled = true,
    )
}