package com.walletline.presentation.screens.auth.pattern.make_pattern

import com.walletline.domain.model.PatternValidationMessage

data class MakePatternState(
    val pattern: String = "",
    val confirmPattern: String = "",
    val patternTitle: Int = 0,
    val remainedTimeToLock: Int = 5,
    val isVisibleWrongPattern: Boolean,
    val wrongPatternErrorString: Int = 0,
    val patternError: PatternValidationMessage? = null,
    val isEntered: Boolean = false,
    val isScrollEnabled: Boolean = false,
    val isContinueButtonEnable: Boolean = false,
    val isActionsEnabled: Boolean = true,
    val isConfirmButtonEnable: Boolean = false,
) {
    constructor() : this(
        pattern = "",
        patternTitle = 0,
        remainedTimeToLock = 5,
        isVisibleWrongPattern = false,
        wrongPatternErrorString = 0,
        patternError = null,
        isEntered = false,
        isScrollEnabled = false,
        isContinueButtonEnable = false,
        isActionsEnabled = true
    )
}