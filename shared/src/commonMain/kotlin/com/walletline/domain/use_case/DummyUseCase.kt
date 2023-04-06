package com.walletline.domain.use_case

import com.walletline.data.local.settings.AppSettings
import com.walletline.di.util.CoroutineDispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DummyUseCase(
    private val settings: AppSettings,
    private val dispatchers: CoroutineDispatchers
) {
    suspend fun execute(): String {

        withContext(dispatchers.database) {
            settings.setToken(token = "Hello World")
        }
        delay(1000)
        return settings.getToken()
    }
}