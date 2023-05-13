package com.walletline.domain.use_case.auth

data class AuthUseCase(
    val register: Register,
    val verifyOtp: VerifyOtp,
    val resendOtp: ResendOtp
)