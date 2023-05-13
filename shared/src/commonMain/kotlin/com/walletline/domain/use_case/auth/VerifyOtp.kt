package com.walletline.domain.use_case.auth

import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class VerifyOtp(
    private val authRepository: AuthRepository,
    private val dispatchers: CoroutineDispatchers,
) {

    suspend fun execute(otp: String): Resource<Boolean, Nothing> = coroutineScope {
        val trackCode = async(dispatchers.database) { authRepository.getTrackingCode() }

        withContext(dispatchers.network) {
            authRepository.verifyOtp(otp = otp, tracCode = trackCode.await())
        }.let { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Error.HttpError -> Resource.Error(message = apiResponse.errorBody)
                ApiResponse.Error.NetworkError -> Resource.Error(message = "Network Error")
                ApiResponse.Error.SerializationError -> Resource.Error(message = "Serialization Error")
                is ApiResponse.Success -> {
                    authRepository.setToken(apiResponse.body)
                    Resource.Success(data = true)
                }
            }
        }
    }
}