package com.walletline.domain.use_case.auth

import com.walletline.domain.repository.AuthRepository
import com.walletline.util.Helpers
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.time
import io.mockative.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SetAdmissionTest {

    private lateinit var setAdmission: SetAdmission

    @Mock
    private val authRepository = mock(classOf<AuthRepository>())


    @BeforeTest
    fun setup() {
        setAdmission = SetAdmission(
            authRepository = authRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `user skip pattern and face-finger should set empty pattern and false finger-face`() = runTest(Helpers.testDispatchers.ui) {
        val pattern = ""
        val isFinger = false

        given(authRepository)
            .coroutine { setPattern(pattern) }
            .then {  }

        given(authRepository)
            .coroutine { setIsFingerFace(isFinger) }
            .then {  }

        setAdmission.execute(pattern, isFinger)

        verify(authRepository)
            .coroutine { setPattern(pattern) }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { setIsFingerFace(false) }
            .wasInvoked(exactly = 1.time)
    }


    @Test
    fun `user set pattern should set pattern and false finger-face`() = runTest(Helpers.testDispatchers.ui) {
        val pattern = "1234"
        val isFinger = false

        given(authRepository)
            .coroutine { setPattern(pattern) }
            .then {  }

        given(authRepository)
            .coroutine { setIsFingerFace(isFinger) }
            .then {  }

        setAdmission.execute(pattern, isFinger)

        verify(authRepository)
            .coroutine { setPattern(pattern) }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { setIsFingerFace(false) }
            .wasInvoked(exactly = 1.time)
    }

    @Test
    fun `user set finger should set empty pattern and true finger-face`() = runTest(Helpers.testDispatchers.ui) {
        val pattern = ""
        val isFinger = true

        given(authRepository)
            .coroutine { setPattern(pattern) }
            .then {  }

        given(authRepository)
            .coroutine { setIsFingerFace(isFinger) }
            .then {  }

        setAdmission.execute(pattern, isFinger)

        verify(authRepository)
            .coroutine { setPattern("") }
            .wasInvoked(exactly = 1.time)

        verify(authRepository)
            .coroutine { setIsFingerFace(true) }
            .wasInvoked(exactly = 1.time)
    }
}