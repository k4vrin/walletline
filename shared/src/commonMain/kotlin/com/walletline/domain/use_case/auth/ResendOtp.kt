package com.walletline.domain.use_case.auth

import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.OtpData
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

class ResendOtp(
    private val authRepository: AuthRepository,
    private val dispatchers: CoroutineDispatchers
) {
    suspend fun execute(): Resource<OtpData, Nothing> {
        val devCode = withTimeout(10.seconds) {
            authRepository.getDevCode()
        }
        if (devCode.isBlank()) return Resource.Error(message = "Server Error")
        return withContext(dispatchers.network) {
            authRepository.getOtp(devCode)
        }.let {
            when (it) {
                is ApiResponse.Error.HttpError -> Resource.Error(message = "Server Error")
                ApiResponse.Error.NetworkError -> Resource.Error(message = "Network Error")
                ApiResponse.Error.SerializationError -> Resource.Error(message = "Serialization Error")
                is ApiResponse.Success -> Resource.Success(OtpData(it.body))
            }
        }
    }
}