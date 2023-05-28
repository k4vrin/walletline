package com.walletline.domain.util

import android.util.Patterns

actual class EmailPatternCheckerImpl : EmailPatternChecker {
    actual override fun isValidEmailPattern(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}