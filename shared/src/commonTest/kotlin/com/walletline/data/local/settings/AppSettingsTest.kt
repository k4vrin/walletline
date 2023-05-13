package com.walletline.data.local.settings

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AppSettingsTest {

    private val settings = MPAppSettings(MapSettings().toSuspendSettings(Helpers.testDispatchers.ui))

    @Test
    fun `successfully set tracking code and get tracking code`() = runTest(Helpers.testDispatchers.ui) {
        val value = "1234567"

        settings.setTrackingCode(value)

        settings.getTrackCode() shouldBe value
    }

    @Test
    fun `successfully set token  and get token `() = runTest(Helpers.testDispatchers.ui) {
        val value = "1234567"

        settings.setToken(value)

        settings.getToken() shouldBe value
    }

}