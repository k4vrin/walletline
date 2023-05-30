package com.walletline.data.repository

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.request.VerifyOtpReq
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.data.dto.response.VerifyOtpRes
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.remote.firebase.auth.FirebaseAuthClient
import com.walletline.data.remote.server.AuthService
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.model.SignInResult
import com.walletline.domain.model.SocialSignInError
import com.walletline.domain.model.SocialSignType
import com.walletline.domain.model.UserData
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

    @Mock
    private val firebaseAuth = mock(classOf<FirebaseAuthClient>())
    private lateinit var authRepository: AuthRepository

    @BeforeTest
    fun setup() {
        authRepository = AuthRepositoryImpl(authService, appSettings, firebaseAuth)
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

    @Test
    fun `successfully sign in with google should return SingResult with user`() = runTest {
        val googleAuthTokens = SocialSignType.GoogleAuth(idToken = "1234", accessToken = null)

        given(firebaseAuth)
            .coroutine { signInWithGoogle(googleAuthTokens) }
            .then { successSignInResult }

        val res = authRepository.signInWithGoogle(googleAuthTokens)

        verify(firebaseAuth)
            .coroutine { signInWithGoogle(googleAuthTokens) }
            .wasInvoked(exactly = 1.time)

        res shouldBe successSignInResult

    }

    @Test
    fun `unsuccessfully sign in with google should return SingResult with null user`() = runTest {
        val googleAuthTokens = SocialSignType.GoogleAuth(idToken = "1234", accessToken = null)

        given(firebaseAuth)
            .coroutine { signInWithGoogle(googleAuthTokens) }
            .then { failureSignInResult }

        val res = authRepository.signInWithGoogle(googleAuthTokens)

        verify(firebaseAuth)
            .coroutine { signInWithGoogle(googleAuthTokens) }
            .wasInvoked(exactly = 1.time)

        res shouldBe failureSignInResult

    }

    @Test
    fun `successfully sign in with facebook should return SingResult with user`() = runTest {
        val facebookAuthTokens = SocialSignType.FacebookAuth(accessToken = "123")

        given(firebaseAuth)
            .coroutine { signInWithFacebook(facebookAuthTokens) }
            .then { successSignInResult }

        val res = authRepository.signInWithFacebook(facebookAuthTokens)

        verify(firebaseAuth)
            .coroutine { signInWithFacebook(facebookAuthTokens) }
            .wasInvoked(exactly = 1.time)

        res shouldBe successSignInResult

    }

    @Test
    fun `unsuccessfully sign in with facebook should return SingResult with null user`() = runTest {
        val facebookAuthTokens = SocialSignType.FacebookAuth(accessToken = "2137")

        given(firebaseAuth)
            .coroutine { signInWithFacebook(facebookAuthTokens) }
            .then { failureSignInResult }

        val res = authRepository.signInWithFacebook(facebookAuthTokens)

        verify(firebaseAuth)
            .coroutine { signInWithFacebook(facebookAuthTokens) }
            .wasInvoked(exactly = 1.time)

        res shouldBe failureSignInResult

    }

    @Test
    fun `successfully get current user id should return non null string`() = runTest {

        val id = "1234"
        given(firebaseAuth)
            .coroutine { getCurrentUserIDToken() }
            .then { id }

        val res = authRepository.getCurrentUserFirebaseID()

        verify(firebaseAuth)
            .coroutine { getCurrentUserIDToken() }
            .wasInvoked(exactly = 1.time)

        res shouldBe id

    }

    @Test
    fun `unsuccessfully get current user id should return null string`() = runTest {

        val id: String? = null
        given(firebaseAuth)
            .coroutine { getCurrentUserIDToken() }
            .then { id }

        val res = authRepository.getCurrentUserFirebaseID()

        verify(firebaseAuth)
            .coroutine { getCurrentUserIDToken() }
            .wasInvoked(exactly = 1.time)

        res shouldBe null

    }

    companion object {
        val successSignInResult = SignInResult(
            data = UserData(
                userId = "homero",
                username = null,
                profilePicUrl = null
            ),
            errorMessage = null
        )

        val failureSignInResult = SignInResult(
            data = null,
            errorMessage = SocialSignInError.UnknownError
        )
    }

    @Test
    fun `successful set and get pattern`() = runTest {
        val value = "129564"
        given(appSettings)
            .coroutine { setPattern(value) }
            .then { }
        authRepository.setPattern(value)
        verify(appSettings)
            .coroutine { setPattern(value) }
            .wasInvoked(exactly = 1.time)

        given(appSettings)
            .coroutine { getPattern() }
            .then { value }

        val res = authRepository.getPattern()

        res shouldBe value

    }

    @Test
    fun `successful set and get isFinger`() = runTest {
        val value = true
        given(appSettings)
            .coroutine { setIsFingerprint(value) }
            .then { }
        authRepository.setIsFingerFace(value)
        verify(appSettings)
            .coroutine { setIsFingerprint(value) }
            .wasInvoked(exactly = 1.time)

        given(appSettings)
            .coroutine { getIsFingerprint() }
            .then { value }

        val res = authRepository.getIsFingerprint()

        res shouldBe value

    }

}