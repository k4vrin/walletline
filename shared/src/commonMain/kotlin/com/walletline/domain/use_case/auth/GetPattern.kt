package com.walletline.domain.use_case.auth

import com.walletline.domain.repository.AuthRepository

class GetPattern(
    private val authRepository: AuthRepository
) {
    suspend fun execute(): String = authRepository.getPattern()
}