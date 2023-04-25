package com.walletline.data.repository

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.data.remote.AuthService
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.repository.AuthRepository
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AuthRepositoryTest {

    @Mock
    private val authService = mock(classOf<AuthService>())
    private lateinit var authRepository: AuthRepository

    @BeforeTest
    fun setup() {
        authRepository = AuthRepositoryImpl(authService)
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


}