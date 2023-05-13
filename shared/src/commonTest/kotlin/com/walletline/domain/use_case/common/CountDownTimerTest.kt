package com.walletline.domain.use_case.common

import app.cash.turbine.test
import app.cash.turbine.testIn
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CountDownTimerTest {

    private lateinit var countDownTimer: CountDownTimer

    @BeforeTest
    fun setup() {
        countDownTimer = CountDownTimer()
    }

    @Test
    fun `Consume all events should happen`() = runTest {
        countDownTimer.start()
        countDownTimer.timer?.test {
            for (i in (120 downTo 0)) {
                awaitItem() shouldBe i
            }
            awaitComplete()
        }
    }

    @Test
    fun `call start should restart the timer`() = runTest {
        countDownTimer.timer?.testIn(backgroundScope)?.apply {
            countDownTimer.start()
            awaitItem() shouldBe 120
            awaitItem() shouldBe 119
            countDownTimer.start()
            awaitItem() shouldBe 120
        }
    }
}