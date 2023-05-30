package com.walletline.domain.use_case.auth

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.UserCondition
import com.walletline.domain.repository.AuthRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetAdmission(
    private val authRepository: AuthRepository,
    private val dispatchers: CoroutineDispatchers
) {
    @NativeCoroutines
    suspend fun execute(): UserCondition = coroutineScope {

        // FIXME: The registration and sign in process is not completed yet
        val isSignedIn = false
        val pattern = async(dispatchers.database) { authRepository.getPattern() }
        val isFingerPrint = async(dispatchers.database) { authRepository.getIsFingerprint() }
        val isOnBoarded = async(dispatchers.database) { authRepository.getOnBoarded() }

        when {
            !isOnBoarded.await() -> UserCondition.FirstTime
            !isSignedIn -> UserCondition.OnBoarded
            pattern.await().isNotBlank() -> UserCondition.SignedInWithPattern
            isFingerPrint.await() -> UserCondition.SignedInWithFinger
            else -> UserCondition.SignedIn
        }
    }
}