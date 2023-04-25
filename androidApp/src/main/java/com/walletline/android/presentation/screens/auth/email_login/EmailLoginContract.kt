package com.walletline.android.presentation.screens.auth.email_login

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.presentation.screens.auth.email_login.EmailLoginState

interface EmailLoginContract :
    UnidirectionalViewModel<EmailLoginState, EmailLoginContract.Effect, EmailLoginContract.Event> {
    sealed interface Event {
        data class EmailChange(val text: String) : Event
        object OnContinueClicked : Event
        object OnEnterBySocialClicked : Event
    }

    sealed interface Effect {
        object RegisterSuccessful : Effect
        object EnterBySocial : Effect
        data class Error(val message: String) : Effect
    }
    }