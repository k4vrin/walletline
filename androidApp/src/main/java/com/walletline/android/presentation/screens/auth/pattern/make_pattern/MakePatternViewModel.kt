package com.walletline.android.presentation.screens.auth.pattern.make_pattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.model.BiometricType
import com.walletline.domain.model.PatternValidationMessage
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.presentation.screens.auth.pattern.make_pattern.MakePatternState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MakePatternViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel(), MakePatternContract {


    private val _state = MutableStateFlow(MakePatternState())
    override val state: StateFlow<MakePatternState> = _state.asStateFlow()

    private val effectChannel = Channel<MakePatternContract.Effect>()
    override val effect: Flow<MakePatternContract.Effect> = effectChannel.receiveAsFlow()

    private val clickState = Channel<MakePatternContract.Event>()

    init {
        clickState
            .receiveAsFlow()
            .debounce(400)
            .onEach { event ->
                when (event) {
                    MakePatternContract.Event.ContinueClicked -> effectChannel.send(
                        MakePatternContract.Effect.NavigateToConfirmPattern
                    )

                    MakePatternContract.Event.FingerPrintClicked -> effectChannel.send(
                        MakePatternContract.Effect.ShowBiometricPrompt
                    )

                    MakePatternContract.Event.ConfirmPatternClicked -> confirmPattern()
                    MakePatternContract.Event.RetryPatternClicked -> effectChannel.send(
                        MakePatternContract.Effect.RetryPattern
                    )

                    MakePatternContract.Event.SkipPatternClicked -> handleSkipPattern()

                    is MakePatternContract.Event.FingerFaceSuccessful -> Unit
                    is MakePatternContract.Event.FingerFaceUnSuccessful -> Unit
                    is MakePatternContract.Event.PatternChanged -> Unit
                    is MakePatternContract.Event.ConfirmPatternChange -> Unit
                }
            }
            .launchIn(viewModelScope)
    }


    override fun onEvent(event: MakePatternContract.Event) {
        when (event) {
            is MakePatternContract.Event.PatternChanged -> updatePatternAndButtonState(event)
            is MakePatternContract.Event.ConfirmPatternChange -> updatePatternAndButtonState(event)
            MakePatternContract.Event.ContinueClicked,
            MakePatternContract.Event.FingerPrintClicked,
            MakePatternContract.Event.ConfirmPatternClicked,
            MakePatternContract.Event.RetryPatternClicked,
            MakePatternContract.Event.SkipPatternClicked,
            -> viewModelScope.launch { clickState.send(event) }

            is MakePatternContract.Event.FingerFaceSuccessful -> handleBiometricSuccess(event.type)
            is MakePatternContract.Event.FingerFaceUnSuccessful -> TODO()
        }
    }

    private fun handleSkipPattern() {
        viewModelScope.launch {
            authUseCase.setAdmission.execute(pattern = "", isFingerprint = false)
            effectChannel.send(MakePatternContract.Effect.SkipMakePattern)
        }
    }

    private fun confirmPattern() {
        if (state.value.pattern != state.value.confirmPattern) {
            effectChannel.trySend(
                element = MakePatternContract.Effect.ConfirmPatternUnSuccessful(
                    message = PatternValidationMessage.NotSame
                )
            )
            return
        }
        viewModelScope.launch {
            authUseCase.setAdmission.execute(
                pattern = state.value.confirmPattern,
                isFingerprint = false
            )
            effectChannel.send(MakePatternContract.Effect.ConfirmPatternSuccessful)
        }
    }

    private fun handleBiometricSuccess(type: BiometricType) {
        viewModelScope.launch {
            authUseCase.setAdmission.execute(pattern = "", isFingerprint = true)
            effectChannel.send(MakePatternContract.Effect.BiometricSuccess)
        }
    }

    private fun updatePatternAndButtonState(event: MakePatternContract.Event) {
        when (event) {
            is MakePatternContract.Event.PatternChanged -> _state.update { state ->
                state.copy(
                    pattern = event.pattern,
                    isScrollEnabled = event.isScrollEnabled,
                    isContinueButtonEnable = event.pattern.length >= 4
                )
            }

            is MakePatternContract.Event.ConfirmPatternChange -> _state.update { state ->
                state.copy(
                    confirmPattern = event.pattern,
                    isScrollEnabled = event.isScrollEnabled,
                    isContinueButtonEnable = event.pattern.length >= 4
                )
            }

            else -> Unit
        }

    }

}