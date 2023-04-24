package com.walletline.di

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.validator.ValidateUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    private val authUseCase: AuthUseCase by inject()
    private val validateUseCase: ValidateUseCase by inject()

    @NativeCoroutines
    suspend fun register(email: String) = authUseCase.register.execute(email)
    fun validateEmail(email: String) = validateUseCase.validateEmail.execute(email)
}

fun initIosKoin() = initKoin()