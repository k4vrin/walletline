package com.walletline.android.di

import com.walletline.android.presentation.screens.auth.email_login.EmailLoginViewModel
import com.walletline.android.presentation.screens.auth.pattern.login_pattern.LoginPatternViewModel
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternViewModel
import com.walletline.android.presentation.screens.auth.social_login.SocialLoginViewModel
import com.walletline.android.presentation.screens.auth.verify_email.VerifyEmailViewModel
import com.walletline.android.presentation.screens.intro.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::IntroViewModel)
    viewModelOf(::EmailLoginViewModel)
    viewModelOf(::VerifyEmailViewModel)
    viewModelOf(::SocialLoginViewModel)
    viewModelOf(::MakePatternViewModel)
    viewModelOf(::LoginPatternViewModel)
}