package com.walletline.android.presentation.screens.auth.social_login

import androidx.lifecycle.ViewModel
import com.walletline.domain.use_case.auth.AuthUseCase

class SocialLoginViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {}