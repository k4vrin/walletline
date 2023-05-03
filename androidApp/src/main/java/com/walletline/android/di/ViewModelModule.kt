package com.walletline.android.di

import com.walletline.android.presentation.screens.auth.email_login.EmailLoginViewModel
import com.walletline.android.presentation.screens.auth.social_login.SocialLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SocialLoginViewModel)
    viewModelOf(::EmailLoginViewModel)
}