package com.walletline.domain.use_case.auth

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.domain.repository.AuthRepository

class SetOnBoarded(
    private val authRepository: AuthRepository
) {
    @NativeCoroutines
    suspend fun execute(isOnBoolean: Boolean) {
        authRepository.setOnBoarded(isOnBoolean)
    }
}