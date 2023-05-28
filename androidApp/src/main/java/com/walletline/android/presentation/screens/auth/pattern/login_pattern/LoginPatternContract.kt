package com.walletline.android.presentation.screens.auth.pattern.login_pattern

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.presentation.screens.auth.pattern.login_pattern.LoginPatternState

interface LoginPatternContract :
    UnidirectionalViewModel<LoginPatternState, LoginPatternContract.Effect, LoginPatternContract.Event> {

    sealed interface Event {
        object DrawPatternStart : Event
        data class DrawPatternFinished(val pattern: String) : Event
    }

    sealed interface Effect {
        object PatternIsVerified : Effect
        object LockApp : Effect
        object LockIsExpired : Effect
    }
}