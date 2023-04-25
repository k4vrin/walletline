package com.walletline.data.repository

import com.walletline.data.local.device.Device
import com.walletline.domain.repository.DeviceRepository

class DeviceRepositoryImpl(
    private val device: Device
) : DeviceRepository {
    override fun getDeviceName(): String = device.getDeviceName()
}