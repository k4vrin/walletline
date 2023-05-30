package com.walletline.presentation.screens.auth.social_login

data class SocialLoginState(
    val isLoading: Boolean = false,
) {
    constructor() : this(
        isLoading = false
    )
}