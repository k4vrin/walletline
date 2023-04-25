package com.walletline.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterSuccess(
    @SerialName("developer_code")
    val developer_code: String? = null,
    @SerialName("tracking_code")
    val tracking_code: String? = null,
)

@Serializable
data class RegisterError(
    @SerialName("email")
    val emailErrors: List<String>? = null,
    @SerialName("device_name")
    val deviceErrors: List<String>? = null
)