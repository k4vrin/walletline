package com.walletline.data.local.device

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class DeviceTest {

    private lateinit var device: Device

    @Before
    fun setup() {
        device = DeviceImpl()
    }

    @Test
     fun getDeviceName_does_not_return_empty_string() {
        assertTrue { device.getDeviceName().isNotBlank() }
    }

}