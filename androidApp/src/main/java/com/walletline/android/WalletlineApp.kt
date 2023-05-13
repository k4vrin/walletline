package com.walletline.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.walletline.android.di.uiModule
import com.walletline.android.di.viewModelModule
import com.walletline.android.presentation.notification.OtpNotification
import com.walletline.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WalletlineApp: Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        initKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@WalletlineApp)
            modules(viewModelModule, uiModule)
        }
    }

    private fun createNotificationChannel() {
        // We don't need channel in apis below O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val otpChannel = NotificationChannel(
                /* id = */ OtpNotification.OtpChannelId,
                /* name = */ "Otp",
                /* importance = */ NotificationManager.IMPORTANCE_DEFAULT
            )
            otpChannel.description = "Used for receiving verification code in development stage"
            otpChannel.importance = NotificationManager.IMPORTANCE_HIGH

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(otpChannel)
        }
    }
}