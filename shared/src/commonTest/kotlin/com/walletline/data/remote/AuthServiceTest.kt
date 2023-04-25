package com.walletline.data.remote

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.response.GetOtpRes
import com.walletline.data.dto.response.RegisterError
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.domain.model.ApiResponse
import com.walletline.util.mockRequest
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ConnectTimeoutException
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import kotlin.test.Test

class AuthServiceTest {

    @Test
    fun `successful register request should return ApiResponse Success`() = runTest {
        val service = mockRequest { reqData ->
            respond(
                content = """
                    {
                        "developer_code":"test",
                        "tracking_code":"test"
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(name = HttpHeaders.ContentType, value = "application/json")
            )
        }

        val response =
            service.register(RegisterReq(email = "test@Test.com", deviceName = "Iphone6s"))

        response shouldBe ApiResponse.Success(
            RegisterSuccess(
                developer_code = "test",
                tracking_code = "test"
            )
        )
    }

    @Test
    fun `BadRequest -missing email- register should return ApiResponse Error HttpError`() = runTest {
        val service = mockRequest { reqData ->
            respond(
                content = """
                    {
                        "email":["error"]
                    }
                """.trimIndent(),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(name = HttpHeaders.ContentType, value = "application/json")
            )
        }

        val response = service.register(RegisterReq(email = "", deviceName = "Iphone6s"))

        response shouldBe ApiResponse.Error.HttpError(
            code = 400,
            errorBody = RegisterError(emailErrors = listOf("error"), deviceErrors = null)
        )
    }

    @Test
    fun `network issue should return NetworkError`() = runTest {
        val service = mockRequest { reqData ->
            throw ConnectTimeoutException(request = reqData)
        }

        val response = service.register(RegisterReq(email = "", deviceName = "Iphone6s"))

        response shouldBe ApiResponse.Error.NetworkError
    }

    @Test
    fun `serialization issue should return SerializationError`() = runTest {
        val service = mockRequest { reqData ->
            throw SerializationException()
        }

        val response = service.register(RegisterReq(email = "", deviceName = "Iphone6s"))

        response shouldBe ApiResponse.Error.SerializationError
    }

    @Test
    fun `successful getOTP request should return ApiResponse Success`() = runTest {
        val service = mockRequest { reqData ->
            respond(
                content = """
                    {
                        "otp":"4444"
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(name = HttpHeaders.ContentType, value = "application/json")
            )
        }

        val response =
            service.getOtp(devCode = "")

        response shouldBe ApiResponse.Success(GetOtpRes("4444"))
    }


}