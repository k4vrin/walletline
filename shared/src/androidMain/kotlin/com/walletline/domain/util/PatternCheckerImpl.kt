package com.walletline.domain.util

import android.util.Patterns

actual class PatternCheckerImpl : PatternChecker {
    actual override fun isValidEmailPattern(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}