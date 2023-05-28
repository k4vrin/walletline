package com.walletline.presentation.screens.auth.admission

import com.walletline.domain.model.UserCondition

data class IntroState(
    val userCondition: UserCondition = UserCondition.FirstTime,
    val isLoading: Boolean = true
) {
    constructor(): this(
        userCondition = UserCondition.FirstTime,
        isLoading = true
    )
}


