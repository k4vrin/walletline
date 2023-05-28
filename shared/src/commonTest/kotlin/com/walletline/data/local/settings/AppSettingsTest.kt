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

    @Test
    fun `successfully set pattern  and get pattern `() = runTest(Helpers.testDispatchers.ui) {
        val value = "1234567"

        settings.setPattern(value)

        settings.getPattern() shouldBe value
    }

    @Test
    fun `successfully set finger  and get finger `() = runTest(Helpers.testDispatchers.ui) {
        val value = true

        settings.setIsFingerprint(value)

        settings.getIsFingerprint() shouldBe value
    }

    @Test
    fun `successfully set boarded  and get boarded `() = runTest(Helpers.testDispatchers.ui) {
        val value = true

        settings.setIsOnBoarded(value)

        settings.getIsOnBoarded() shouldBe value
    }

}