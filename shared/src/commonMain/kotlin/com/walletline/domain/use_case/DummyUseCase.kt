package com.walletline.domain.use_case

import kotlinx.coroutines.delay

class DummyUseCase {
    suspend fun execute(): String {
        delay(500)
        return "Hello World"
    }
}