package com.walletline.android.presentation.screens.auth.pattern.make_pattern

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.BiometricError
import com.walletline.domain.model.BiometricType
import com.walletline.domain.model.PatternValidationMessage
import com.walletline.presentation.screens.auth.pattern.make_pattern.MakePatternState

interface MakePatternContract :
    UnidirectionalViewModel<MakePatternState, MakePatternContract.Effect, MakePatternContract.Event> {
    sealed interface Event {
        data class PatternChanged(val pattern: String, val isScrollEnabled: Boolean) : Event
        data class ConfirmPatternChange(val pattern: String, val isScrollEnabled: Boolean) : Event
        object SkipPatternClicked : Event
        object ContinueClicked : Event
        object FingerPrintClicked : Event
        object ConfirmPatternClicked : Event
        object RetryPatternClicked : Event
        data class FingerFaceSuccessful(val type: BiometricType) : Event
        data class FingerFaceUnSuccessful(val error: BiometricError) : Event
    }

    sealed interface Effect {
        object NavigateToConfirmPattern : Effect
        object ConfirmPatternSuccessful : Effect
        object BiometricSuccess : Effect
        data class ConfirmPatternUnSuccessful(val message: PatternValidationMessage) : Effect
        object RetryPattern : Effect
        object ShowBiometricPrompt : Effect
        object SkipMakePattern : Effect
    }
}