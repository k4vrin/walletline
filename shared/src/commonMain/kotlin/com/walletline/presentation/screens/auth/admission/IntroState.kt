package com.walletline.presentation.screens.auth.admission

import com.walletline.domain.model.auth.UserCondition

data class IntroState(
    val userCondition: UserCondition = UserCondition.FirstTime,
    val isLoading: Boolean = true
) {
    constructor(): this(
        userCondition = UserCondition.FirstTime,
        isLoading = true
    )
}


