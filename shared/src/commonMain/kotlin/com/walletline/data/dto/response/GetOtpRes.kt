package com.walletline.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class GetOtpRes(
    val otp: String
)
