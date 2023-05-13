package com.walletline.presentation.screens.auth.email_login

import com.walletline.domain.model.EmailValidationMessage

data class EmailLoginState(
    val email: String = "",
    val emailError: EmailValidationMessage? = null,
    val isLoading: Boolean = false,
    val isActionsEnabled: Boolean = true,
) {
    constructor() : this(
        email = "",
        emailError = null,
        isLoading = false,
        isActionsEnabled = true,
    )
}