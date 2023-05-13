package com.walletline.data.repository

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.request.VerifyOtpReq
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.data.dto.response.VerifyOtpRes
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.remote.AuthService
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.repository.AuthRepository
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.time
import io.mockative.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AuthRepositoryTest {

    @Mock
    private val authService = mock(classOf<AuthService>())
    @Mock
    private val appSettings = mock(classOf<AppSettings>())
    private lateinit var authRepository: AuthRepository

    @BeforeTest
    fun setup() {
        authRepository = AuthRepositoryImpl(authService, appSettings)
    }


    @Test
    fun `successful register return ApiResponse Success Registered`() = runTest {
        given(authService)
            .coroutine { register(RegisterReq("", "")) }
            .then { ApiResponse.Success(RegisterSuccess()) }

        val res = authRepository.register("", "")

        res shouldBe ApiResponse.Success(RegisteredSuccess())
    }

    @Test
    fun `error register return ApiResponse Error`() = runTest {
        given(authService)
            .coroutine { register(RegisterReq("", "")) }
            .then { ApiResponse.Error.NetworkError }

        val res = authRepository.register("", "")

        assertTrue {
            res is ApiResponse.Error
        }
    }

    @Test
    fun `successful verifyOTP return ApiResponse Success String`() = runTest {
        given(authService)
            .coroutine { verifyOtp(VerifyOtpReq("1234", "1234")) }
            .then { ApiResponse.Success(VerifyOtpRes(accessToken = "2345", expiresAt = "")) }

        val res = authRepository.verifyOtp("1234", tracCode = "1234")

        res shouldBe ApiResponse.Success(body = "2345")
    }

    @Test
    fun `successful verifyOTP but null access token return ApiResponse Error Network`() = runTest {
        given(authService)
            .coroutine { verifyOtp(VerifyOtpReq("1234", "1234")) }
            .then { ApiResponse.Success(VerifyOtpRes(accessToken = null, expiresAt = null)) }

        val res = authRepository.verifyOtp("1234", tracCode = "1234")

        res shouldBe ApiResponse.Error.NetworkError
    }

    @Test
    fun `successful get tracking code`() = runTest {
        val trackCode = "123456789564"
        given(appSettings)
            .coroutine { getTrackCode() }
            .then { trackCode }

        val res = authRepository.getTrackingCode()

        res shouldBe trackCode
    }

    @Test
    fun `successful set tracking code`() = runTest {
        val trackCode = "123456789564"
        given(appSettings)
            .coroutine { setTrackingCode(trackCode) }
            .then { Unit }
        authRepository.setTrackingCode(trackCode)
        verify(appSettings)
            .coroutine { setTrackingCode(trackCode) }
            .wasInvoked(exactly = 1.time)

    }

    @Test
    fun `successful get token`() = runTest {
        val token = "123456789564"
        given(appSettings)
            .coroutine { getToken() }
            .then { token }

        val res = authRepository.getToken()

        res shouldBe token
    }

    @Test
    fun `successful set token`() = runTest {
        val token = "123456789564"
        given(appSettings)
            .coroutine { setToken(token) }
            .then { Unit }
        authRepository.setToken(token)
        verify(appSettings)
            .coroutine { setToken(token) }
            .wasInvoked(exactly = 1.time)

    }

}