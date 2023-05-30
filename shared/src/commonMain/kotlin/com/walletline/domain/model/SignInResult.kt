package com.walletline.domain.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: SocialSignInError?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePicUrl: String?
)
