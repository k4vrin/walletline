package com.walletline.domain.repository

import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.model.RegisteredSuccess

interface AuthRepository {
    suspend fun register(email: String, deviceName: String): ApiResponse<RegisteredSuccess, RegisteredError>
    suspend fun getOtp(devCode: String): ApiResponse<String, String>
}