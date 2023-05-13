package com.walletline.domain.use_case.auth

import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.OtpData
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.repository.DeviceRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class Register(
    private val dispatchers: CoroutineDispatchers,
    private val authRepository: AuthRepository,
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(email: String): Resource<OtpData, RegisteredError> = coroutineScope {

        val deviceName = async(dispatchers.database) { deviceRepository.getDeviceName() }

        withContext(dispatchers.network) {
            authRepository.register(email, deviceName.await())
        }.let {
            when(it) {
                is ApiResponse.Error.HttpError -> Resource.Error(data = it.errorBody)
                ApiResponse.Error.NetworkError -> Resource.Error(message = "Network Error")
                ApiResponse.Error.SerializationError -> Resource.Error(message = "Serialization Error")
                is ApiResponse.Success -> getOTP(it.body.devCode) {
                    authRepository.setTrackingCode(it.body.trackCode ?: "")
                    authRepository.setDevCode(it.body.devCode ?: "")
                }
            }
        }
    }

    private suspend inline fun getOTP(devCode: String?, ifOtpSuccess: () -> Unit): Resource<OtpData, RegisteredError> {
        if (devCode.isNullOrBlank()) return Resource.Error(message = "Server Error")
        return withContext(dispatchers.network) {
            authRepository.getOtp(devCode)
        }.let {
            when (it) {
                is ApiResponse.Error.HttpError -> Resource.Error(message = "Server Error")
                ApiResponse.Error.NetworkError -> Resource.Error(message = "Network Error")
                ApiResponse.Error.SerializationError -> Resource.Error(message = "Serialization Error")
                is ApiResponse.Success -> {
                    ifOtpSuccess()
                    Resource.Success(OtpData(otp = it.body))
                }
            }
        }
    }
}