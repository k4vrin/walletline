package com.walletline.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReq(
    val email: String,
    @SerialName("device_name")
    val deviceName: String
)
