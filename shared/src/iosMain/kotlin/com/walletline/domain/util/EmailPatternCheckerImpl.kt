package com.walletline.domain.util

actual class EmailPatternCheckerImpl : EmailPatternChecker {
    actual override fun isValidEmailPattern(email: String): Boolean {
        val emailRegEx = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        return email.matches(emailRegEx.toRegex())
    }
}