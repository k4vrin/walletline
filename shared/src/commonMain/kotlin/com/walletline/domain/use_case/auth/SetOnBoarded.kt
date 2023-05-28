package com.walletline.domain.use_case.auth

import com.walletline.domain.repository.AuthRepository

class SetOnBoarded(
    private val authRepository: AuthRepository
) {
    suspend fun execute(isOnBoolean: Boolean) {
        authRepository.setOnBoarded(isOnBoolean)
    }
}