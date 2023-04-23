package com.walletline.data.local.device

import platform.UIKit.UIDevice

actual class DeviceImpl: Device {
    override fun getDeviceName(): String {
        return UIDevice.currentDevice.name
    }
}