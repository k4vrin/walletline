package com.walletline.domain.use_case.validator

import com.walletline.domain.model.EmailValidationMessage
import com.walletline.domain.util.PatternChecker
import com.walletline.domain.util.ValidationResult

class ValidateEmail(
    private val patternChecker: PatternChecker
) {
    fun execute(email: String): ValidationResult<EmailValidationMessage> {
        return when {
            email.isBlank() -> ValidationResult(isSuccess = false, message = EmailValidationMessage.NotEmpty)
            !patternChecker.isValidEmailPattern(email) -> ValidationResult(isSuccess = false, message = EmailValidationMessage.NotValid)
            else -> ValidationResult(isSuccess = true)
        }
    }
}


