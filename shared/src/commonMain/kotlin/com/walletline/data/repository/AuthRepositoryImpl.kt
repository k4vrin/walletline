package com.walletline.data.repository

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.mapper.toDomain
import com.walletline.data.remote.AuthService
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun register(
        email: String,
        deviceName: String,
    ): ApiResponse<RegisteredSuccess, RegisteredError> {
        return authService.register(
            RegisterReq(email, deviceName)
        ).toDomain()
    }

    override suspend fun getOtp(devCode: String): ApiResponse<String, String> {
        return authService.getOtp(devCode).let {
            when (it) {
                ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
                is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(it.code, errorBody = null)
                is ApiResponse.Success -> ApiResponse.Success(body = it.body.otp)
                ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            }
        }
    }
}