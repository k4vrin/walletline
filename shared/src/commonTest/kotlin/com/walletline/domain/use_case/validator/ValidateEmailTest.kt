package com.walletline.domain.use_case.validator

import com.walletline.domain.model.auth.EmailValidationMessage
import com.walletline.domain.util.EmailPatternChecker
import com.walletline.domain.util.ValidationResult
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlin.test.BeforeTest
import kotlin.test.Test

class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail

    @Mock
    val patternChecker = mock(classOf<EmailPatternChecker>())

    @BeforeTest
    fun setup() {
        validateEmail = ValidateEmail(patternChecker)
    }

    @Test
    fun `Empty email should return EmailValidationMessage NotEmpty as message`() {
        val res = validateEmail.execute("")

        res shouldBe ValidationResult(isSuccess = false, message = EmailValidationMessage.NotEmpty)
    }

    @Test
    fun `invalid email should return EmailValidationMessage NotValid as message`() {
        val email = "213123"
        given(patternChecker)
            .invocation { isValidEmailPattern(email) }
            .then { false }
        val res = validateEmail.execute(email)

        res shouldBe ValidationResult(isSuccess = false, message = EmailValidationMessage.NotValid)
    }

    @Test
    fun `valid email should return success`() {
        val email = "213123@gmail.com"
        given(patternChecker)
            .invocation { isValidEmailPattern(email) }
            .then { true }
        val res = validateEmail.execute(email)

        res shouldBe ValidationResult(isSuccess = true,)
    }
}