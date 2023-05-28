package com.walletline.presentation.screens.auth.pattern.login_pattern

import com.walletline.domain.model.PatternValidationMessage

data class LoginPatternState(
    val pattern: String = "",
    val remainingAttempts: Int = 6,
    val showRemainingAttempts: Boolean = false,
    val isScrollEnabled: Boolean = true,
    val patternError: PatternValidationMessage? = null,
    val lockTimer: Int = 60,
)
