package com.walletline.android.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.walletline.android.di.util.KoinAndroidQualifiers
import com.walletline.android.presentation.social_auth.google_auth.GoogleAuthUiClient
import com.walletline.android.presentation.notification.NotificationService
import com.walletline.android.presentation.notification.OtpNotification
import com.walletline.android.presentation.social_auth.apple_auth.AppleAuthUiClient
import com.walletline.android.presentation.social_auth.facebook_auth.FacebookAuthUiClient
import org.koin.dsl.module

val uiModule = module {
    factory(qualifier = KoinAndroidQualifiers.OtpNotification) { provideOtpNotification(context = get()) }
    single { provideGoogleAuthUiClient(context = get()) }
    single { provideFacebookAuthUiClient() }
    single { provideAppleAuthUiClient() }
}

private fun provideOtpNotification(context: Context): NotificationService = OtpNotification(context)

private fun provideGoogleAuthUiClient(context: Context) =
    GoogleAuthUiClient(
        context = context,
        oneTapClient = Identity.getSignInClient(
            context
        )
    )

private fun provideFacebookAuthUiClient(): FacebookAuthUiClient = FacebookAuthUiClient()

private fun provideAppleAuthUiClient(): AppleAuthUiClient = AppleAuthUiClient()