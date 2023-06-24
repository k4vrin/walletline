package com.walletline.domain.use_case.auth

import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.auth.RegisteredError
import com.walletline.domain.model.auth.RegisteredSuccess
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.repository.DeviceRepository
import com.walletline.domain.util.Resource
import com.walletline.util.Helpers
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

class RegisterTest {

    private lateinit var register: Register

    @Mock
    private val authRepository = mock(classOf<AuthRepository>())

    @Mock
    private val deviceRepository = mock(classOf<DeviceRepository>())

    @BeforeTest
    fun setup() {
        register = Register(
            authRepository = authRepository,
            deviceRepository = deviceRepository,
            dispatchers = Helpers.testDispatchers
        )

        given(deviceRepository)
            .invocation { getDeviceName() }
            .then { deviceName }
    }

    @Test
    fun `register successfully return Resource success OTP and save trackCode`() = runTest(Helpers.testDispatchers.ui) {

        given(authRepository)
            .coroutine { register(email, deviceName) }
            .then { ApiResponse.Success(registeredSuccess) }

        given(authRepository)
            .coroutine { getOtp(registeredSuccess.devCode ?: "") }
            .then { ApiResponse.Success("1234") }

        given(authRepository)
            .coroutine { setTrackingCode("") }
            .then { }

        val res = register.execute(email)

        verify(deviceRepository)
            .invocation { getDeviceName() }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { register(email, deviceName) }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { getOtp(registeredSuccess.devCode ?: "") }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { setTrackingCode("") }
            .wasInvoked(exactly = 1.time)


        (res is Resource.Success) shouldBe true
        val otp = (res as Resource.Success).data.otp
        otp.isNotBlank() shouldBe true
    }


    @Test
    fun `register unsuccessfully return Resource Error `() = runTest(Helpers.testDispatchers.ui) {

        given(authRepository)
            .coroutine { register(email, deviceName) }
            .then { ApiResponse.Error.HttpError(code = 400, errorBody = registeredError) }

        val res = register.execute(email)

        verify(deviceRepository)
            .invocation { getDeviceName() }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { register(email, deviceName) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Error) shouldBe true

    }

    companion object {
        val email = "test@test.com"
        val deviceName = "iPhone SE"
        val registeredSuccess = RegisteredSuccess(devCode = "1234")
        val registeredError = RegisteredError(emailError = listOf("Error"))
    }
}