package com.walletline.android.presentation.screens.intro

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.UserCondition
import com.walletline.presentation.screens.auth.admission.IntroState

interface IntroContract :
    UnidirectionalViewModel<IntroState, IntroContract.Effect, IntroContract.Event> {
    sealed interface Event {
        object UserOnBoarded : Event
    }

    sealed interface Effect {
        object UserOnBoarded : Effect
        data class Navigate(val userCondition: UserCondition) : Effect
    }
}