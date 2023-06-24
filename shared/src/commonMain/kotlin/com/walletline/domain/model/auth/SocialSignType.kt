package com.walletline.domain.model.auth

sealed interface SocialSignType {
    data class GoogleAuth(
        val idToken: String?,
        val accessToken: String?,
    ) : SocialSignType

    data class FacebookAuth(
        val accessToken: String,
    ) : SocialSignType

    data class AppleAuth(
        val verifyToken: String,
    ) : SocialSignType
}