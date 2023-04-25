package com.walletline.domain.util

interface PatternChecker {
    fun isValidEmailPattern(email: String): Boolean
}

expect class PatternCheckerImpl : PatternChecker {
    override fun isValidEmailPattern(email: String): Boolean
}