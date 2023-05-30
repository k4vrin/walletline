package com.walletline.android.presentation.screens.auth.verify_email

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.android.presentation.screens.navArgs
import com.walletline.domain.model.OTPValidationMessage
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.common.CommonUseCase
import com.walletline.domain.util.Resource
import com.walletline.presentation.screens.auth.verify_email.VerifyEmailState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
    savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase,
    private val commonUseCase: CommonUseCase,
) : ViewModel(), VerifyEmailContract {

    private val _state = MutableStateFlow(VerifyEmailState())
    override val state: StateFlow<VerifyEmailState> = _state.asStateFlow()

    private val effectChannel = Channel<VerifyEmailContract.Effect>()
    override val effect: Flow<VerifyEmailContract.Effect> = effectChannel.receiveAsFlow()
    
    init {
        savedStateHandle.navArgs<VerifyEmailNavArg>().let { args ->
            _state.update { state -> state.copy(email = args.email) }
        }

        startTimer()
    }

    override fun onEvent(event: VerifyEmailContract.Event) {
        when (event) {
            is VerifyEmailContract.Event.OTPChange -> _state.update { state -> state.copy(otp = event.text) }
            VerifyEmailContract.Event.OnChangeEmailClicked -> effectChannel.trySend(
                VerifyEmailContract.Effect.NavigateToEmail
            )
            VerifyEmailContract.Event.OnContinueClicked -> verifyOTP()
            VerifyEmailContract.Event.OnPolicyClicked -> effectChannel.trySend(VerifyEmailContract.Effect.ShowPolicy)
            VerifyEmailContract.Event.OnResendClicked -> resendOTP()
            VerifyEmailContract.Event.OnTermsClicked -> effectChannel.trySend(VerifyEmailContract.Effect.ShowTerms)
        }
    }

    private fun startTimer() {
        val countDownTimer = commonUseCase.countDownTimer
        countDownTimer.start()
        countDownTimer.timer
            ?.onEach { tick ->
                _state.update { state ->
                    state.copy(timer = tick, isTimerActive = true)
                }
            }
            ?.onCompletion {
                _state.update { state ->
                    state.copy(isTimerActive = false)
                }
            }
            ?.launchIn(viewModelScope)
    }

    private fun verifyOTP() {
        _state.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.verifyOtp.execute(otp = state.value.otp).let { resource ->
                _state.update { state -> state.copy(isLoading = false) }
                when (resource) {
                    is Resource.Error -> handleVerifyError(resource.message)
                    is Resource.Success -> effectChannel.trySend(VerifyEmailContract.Effect.VerifySuccessful)
                }
            }
        }
    }

    private fun handleVerifyError(message: String?) {
        when {
            message?.contains("otp", ignoreCase = true) == true -> _state.update { it.copy(otpError = OTPValidationMessage.NotValid) }
            else -> effectChannel.trySend(VerifyEmailContract.Effect.ShowError(message ?: "Try again"))
        }
    }

    private fun resendOTP() {
        _state.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            _state.update { state -> state.copy(isLoading = false) }
            authUseCase.resendOtp.execute().let { resource ->
                when (resource) {
                    is Resource.Error -> effectChannel.send(VerifyEmailContract.Effect.ShowError(message = resource.message ?: "Try again"))
                    is Resource.Success -> effectChannel.send(VerifyEmailContract.Effect.ResendOtpSuccess(resource.data.otp)).also { startTimer() }
                }
            }
        }
    }

}