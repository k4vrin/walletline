package com.walletline.domain.use_case.common

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

class CountDownTimer {

    @NativeCoroutines
    var timer: Flow<Int>? = null
        private set


    fun start() {
        timer = null
        timer = (10 downTo 0).asFlow().onEach { delay(1.seconds) }
    }

}