package com.walletline.data.remote.server

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.request.VerifyOtpReq
import com.walletline.data.dto.response.GetOtpRes
import com.walletline.data.dto.response.RegisterError
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.data.dto.response.VerifyOtpError
import com.walletline.data.dto.response.VerifyOtpRes
import com.walletline.data.util.safeRequest
import com.walletline.domain.model.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters

class KtorAuthService(
    private val client: HttpClient,
) : AuthService {

    override suspend fun register(req: RegisterReq): ApiResponse<RegisterSuccess, RegisterError> {
        return client.safeRequest {
            method = HttpMethod.Post
            setBody(req)
            url(HttpRoutes.Register)
        }
    }

    override suspend fun getOtp(devCode: String): ApiResponse<GetOtpRes, String> {
        return client.safeRequest {
            method = HttpMethod.Post
            setBody(FormDataContent(Parameters.build {
                append("developer_code", devCode)
            }))
            url(HttpRoutes.GetOTP)
        }
    }

    override suspend fun verifyOtp(req: VerifyOtpReq): ApiResponse<VerifyOtpRes, VerifyOtpError> {
        return client.safeRequest {
            method = HttpMethod.Post
            setBody(req)
            url(HttpRoutes.VerifyOTP)
        }
    }
}