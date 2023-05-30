package com.walletline.domain.use_case.auth

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.repository.AuthRepository
import kotlinx.coroutines.withContext

class SetAdmission(
    private val authRepository: AuthRepository,
    private val dispatchers: CoroutineDispatchers
) {
    @NativeCoroutines
    suspend fun execute(pattern: String, isFingerprint: Boolean ) {
        when {
            pattern.isNotBlank() -> {
                withContext(dispatchers.database) {
                    authRepository.setPattern(pattern = pattern)
                    authRepository.setIsFingerFace(false)
                }
            }
            isFingerprint -> {
                withContext(dispatchers.database) {
                    authRepository.setPattern(pattern = "")
                    authRepository.setIsFingerFace(true)
                }
            }
            else -> {
                withContext(dispatchers.database) {
                    authRepository.setPattern(pattern = "")
                    authRepository.setIsFingerFace(false)
                }
            }
        }
    }
}
