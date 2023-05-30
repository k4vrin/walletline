package com.walletline.android.presentation.screens.auth.verify_email

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.presentation.screens.auth.verify_email.VerifyEmailState

interface VerifyEmailContract :
    UnidirectionalViewModel<VerifyEmailState, VerifyEmailContract.Effect, VerifyEmailContract.Event> {

    sealed interface Event {
        data class OTPChange(val text: String) : Event
        object OnContinueClicked : Event
        object OnChangeEmailClicked : Event
        object OnResendClicked : Event
        object OnTermsClicked : Event
        object OnPolicyClicked : Event
    }

    sealed interface Effect {
        object VerifySuccessful : Effect
        object NavigateToEmail : Effect
        object ShowPolicy : Effect
        object ShowTerms : Effect
        data class ShowError(val message: String) : Effect
        data class ResendOtpSuccess(val otp: String) : Effect
    }
}