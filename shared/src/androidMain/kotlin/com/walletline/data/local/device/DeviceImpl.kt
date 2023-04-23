package com.walletline.data.local.device

import android.os.Build

actual class DeviceImpl : Device {
    override fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL

        if (model.lowercase().startsWith(manufacturer.lowercase())) {
            return model.replaceFirstChar { it.uppercase() }
        }
        return manufacturer.replaceFirstChar { it.uppercase() } + " " + model
    }
}