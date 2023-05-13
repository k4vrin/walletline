package com.walletline.domain.use_case.auth

import com.walletline.domain.model.ApiResponse
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.util.Resource
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.time
import io.mockative.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class VerifyOtpTest {

    private lateinit var verifyOtp: VerifyOtp

    @Mock
    private val authRepository = mock(classOf<AuthRepository>())

    @BeforeTest
    fun setup() {
        verifyOtp = VerifyOtp(
            authRepository = authRepository,
            dispatchers = Helpers.testDispatchers
        )

        runBlocking {
            given(authRepository)
                .coroutine { getTrackingCode() }
                .then { trackingCode }
        }
    }


    @Test
    fun `Successful verify otp return success and save token`() = runTest(Helpers.testDispatchers.ui) {
        given(authRepository)
            .coroutine { getTrackingCode() }
            .then { trackingCode }
        given(authRepository)
            .coroutine { verifyOtp(otp = otp, tracCode = trackingCode) }
            .then { ApiResponse.Success(token) }
        given(authRepository)
            .coroutine { setToken(token) }
            .then { }

        val res = verifyOtp.execute(otp)

        verify(authRepository)
            .coroutine { getTrackingCode() }
            .wasInvoked(1.time)

        verify(authRepository)
            .coroutine { verifyOtp(otp, trackingCode) }
            .wasInvoked(1.time)

        verify(authRepository)
            .coroutine { setToken(token) }
            .wasInvoked(1.time)

        (res is Resource.Success) shouldBe true
    }

    @Test
    fun `UnSuccessful verify should return Resource Error `() = runTest(Helpers.testDispatchers.ui) {

        given(authRepository)
            .coroutine { verifyOtp(otp, trackingCode) }
            .then { ApiResponse.Error.HttpError(code = 401, errorBody = "invalid otp") }

        val res = verifyOtp.execute(otp = otp)

        verify(authRepository)
            .coroutine { verifyOtp(otp, trackingCode) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Error) shouldBe true

    }

    companion object {
        const val token = "tok8endSer"
        const val otp = "1223"
        const val trackingCode = "123456"
    }
}