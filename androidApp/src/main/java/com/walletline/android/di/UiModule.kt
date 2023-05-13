package com.walletline.android.di

import android.content.Context
import com.walletline.android.di.util.KoinAndroidQualifiers
import com.walletline.android.presentation.notification.NotificationService
import com.walletline.android.presentation.notification.OtpNotification
import org.koin.dsl.module

val uiModule = module {
    factory(qualifier = KoinAndroidQualifiers.OtpNotification) { provideOtpNotification(context = get()) }
}

private fun provideOtpNotification(context: Context): NotificationService = OtpNotification(context)