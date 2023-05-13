package com.walletline.domain.use_case.common

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

class CountDownTimer {

    var timer: Flow<Int>? = null
        private set


    fun start() {
        timer = null
        timer = (5 downTo 0).asFlow().onEach { delay(1.seconds) }
    }

}