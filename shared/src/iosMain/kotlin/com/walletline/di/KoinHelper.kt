package com.walletline.di

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.domain.use_case.DummyUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    private val useCase: DummyUseCase by inject()
    @NativeCoroutines
    suspend fun getDummyUseCase(): String = useCase.execute()
}

fun initIosKoin() = initKoin()