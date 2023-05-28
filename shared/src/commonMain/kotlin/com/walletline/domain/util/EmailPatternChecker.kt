package com.walletline.domain.util

interface EmailPatternChecker {
    fun isValidEmailPattern(email: String): Boolean
}

expect class EmailPatternCheckerImpl : EmailPatternChecker {
    override fun isValidEmailPattern(email: String): Boolean
}