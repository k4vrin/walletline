package com.walletline.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpReq(
    val otp: String,
    val tracking_code: String
)
