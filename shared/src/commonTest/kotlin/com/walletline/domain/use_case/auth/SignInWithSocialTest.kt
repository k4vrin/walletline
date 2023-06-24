package com.walletline.domain.use_case.auth

import com.walletline.domain.model.auth.SignInResult
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.auth.SocialSignType
import com.walletline.domain.model.auth.UserData
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
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SignInWithSocialTest {

    private lateinit var signInWithSocial: SignInWithSocial

    @Mock
    private val authRepository = mock(classOf<AuthRepository>())

    @BeforeTest
    fun setup() {
        signInWithSocial = SignInWithSocial(authRepository, Helpers.testDispatchers)
    }

    @Test
    fun `successfully sign in with google should return Resource Success true`() = runTest(Helpers.testDispatchers.ui) {
        val type = SocialSignType.GoogleAuth(idToken = "123", accessToken = null)
        given(authRepository)
            .coroutine { signInWithGoogle(type) }
            .then { successSignInResult }

        val res = signInWithSocial.execute(type)

        verify(authRepository)
            .coroutine { signInWithGoogle(type) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Success) shouldBe true
        (res as Resource.Success).data shouldBe true
    }

    @Test
    fun `successfully sign in with facebook should return Resource Success true`() = runTest(Helpers.testDispatchers.ui) {
        val type = SocialSignType.FacebookAuth(accessToken = "12312")
        given(authRepository)
            .coroutine { signInWithFacebook(type) }
            .then { successSignInResult }

        val res = signInWithSocial.execute(type)

        verify(authRepository)
            .coroutine { signInWithFacebook(type) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Success) shouldBe true
        (res as Resource.Success).data shouldBe true
    }

    @Test
    fun `unsuccessfully sign in with google should return Resource Error`() = runTest(Helpers.testDispatchers.ui) {
        val type = SocialSignType.GoogleAuth(idToken = "123", accessToken = null)
        val error = SocialSignInError.UnknownError
        given(authRepository)
            .coroutine { signInWithGoogle(type) }
            .then { failureSignInResult(error) }

        val res = signInWithSocial.execute(type)

        verify(authRepository)
            .coroutine { signInWithGoogle(type) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe error
    }

    @Test
    fun `unsuccessfully sign in with facebook should return Resource Error`() = runTest(Helpers.testDispatchers.ui) {
        val type = SocialSignType.FacebookAuth(accessToken = "12312")
        val error = SocialSignInError.UnknownError
        given(authRepository)
            .coroutine { signInWithFacebook(type) }
            .then { failureSignInResult(error) }

        val res = signInWithSocial.execute(type)

        verify(authRepository)
            .coroutine { signInWithFacebook(type) }
            .wasInvoked(exactly = 1.time)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe error
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

        fun failureSignInResult(type: SocialSignInError) = SignInResult(
            data = null,
            errorMessage = type
        )
    }
}