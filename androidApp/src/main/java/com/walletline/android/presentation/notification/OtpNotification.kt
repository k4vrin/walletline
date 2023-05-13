package com.walletline.android.presentation.notification

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.walletline.android.R

class OtpNotification(
    context: Context
) : NotificationService {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    override fun showNotification(context: Context, title: String, content: String) {
        val otpNotification = NotificationCompat.Builder(
            context,
            OtpChannelId
        )
            .setSmallIcon(R.drawable.app_logo_notif)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.app_logo_notif))
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .build()

        notificationManager.notify(OtpNotificationId, otpNotification)
    }

    companion object {
        const val OtpChannelId = "otp_channel"
        const val OtpNotificationId = 40
    }
}