package com.walletline.android.presentation.notification

import android.content.Context

interface NotificationService {

    fun showNotification(context: Context, title: String, content: String)
}