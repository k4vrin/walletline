package com.walletline.domain.use_case.auth

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.domain.repository.AuthRepository

class GetPattern(
    private val authRepository: AuthRepository
) {
    @NativeCoroutines
    suspend fun execute(): String = authRepository.getPattern()
}