package com.walletline.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRes(
    @SerialName("access_token")
    val accessToken: String?,
    @SerialName("expires_at")
    val expiresAt: String?
)


@Serializable
data class VerifyOtpError(
    val message: String?
)