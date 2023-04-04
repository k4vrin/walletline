package com.walletline.di

import com.walletline.domain.use_case.DummyUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    private val useCase: DummyUseCase by inject()
    suspend fun getDummyUseCase(): String = useCase.execute()
}

fun initIosKoin() = initKoin()