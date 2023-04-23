package com.walletline.android.presentation.screens.auth.email_login

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.EmailValidationMessage

interface EmailLoginContract :
    UnidirectionalViewModel<EmailLoginContract.State, EmailLoginContract.Effect, EmailLoginContract.Event> {

    data class State(
        val email: String = "",
        val emailError: EmailValidationMessage? = null,
        val isLoading: Boolean = false,
        val isActionsEnabled: Boolean = true,
    )

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