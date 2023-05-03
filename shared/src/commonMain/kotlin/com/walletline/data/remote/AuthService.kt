package com.walletline.data.remote

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.response.GetOtpRes
import com.walletline.data.dto.response.RegisterError
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.domain.model.ApiResponse

interface AuthService {
    suspend fun register(req: RegisterReq): ApiResponse<RegisterSuccess, RegisterError>
    suspend fun getOtp(devCode: String): ApiResponse<GetOtpRes, String>
}