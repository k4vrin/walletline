package com.walletline.domain.use_case.validator

import com.walletline.domain.model.auth.EmailValidationMessage
import com.walletline.domain.util.EmailPatternChecker
import com.walletline.domain.util.ValidationResult

class ValidateEmail(
    private val emailPatternChecker: EmailPatternChecker
) {
    fun execute(email: String): ValidationResult<EmailValidationMessage> {
        return when {
            email.isBlank() -> ValidationResult(isSuccess = false, message = EmailValidationMessage.NotEmpty)
            !emailPatternChecker.isValidEmailPattern(email) -> ValidationResult(isSuccess = false, message = EmailValidationMessage.NotValid)
            else -> ValidationResult(isSuccess = true)
        }
    }
}


