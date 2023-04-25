package com.walletline.data.local.device

import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeviceTest {

    private lateinit var device: Device

    @BeforeTest
    fun setup() {
        device = DeviceImpl()
    }

    @Test
    fun getDeviceName_does_not_return_empty_string() {
        device.getDeviceName().isNotBlank() shouldBe true
    }
}