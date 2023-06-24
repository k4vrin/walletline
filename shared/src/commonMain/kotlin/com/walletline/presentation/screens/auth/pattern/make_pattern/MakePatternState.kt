package com.walletline.presentation.screens.auth.pattern.make_pattern

import com.walletline.domain.model.auth.PatternValidationMessage

data class MakePatternState(
    val pattern: String = "",
    val confirmPattern: String = "",
    val patternError: PatternValidationMessage? = null,
    val isScrollEnabled: Boolean = false,
    val isContinueButtonEnable: Boolean = false,
    val isConfirmButtonEnable: Boolean = false,

    ) {
    constructor() : this(
        pattern = "",
        confirmPattern = "",
        patternError = null,
        isScrollEnabled = false,
        isContinueButtonEnable = false,
        isConfirmButtonEnable = false
    )
}