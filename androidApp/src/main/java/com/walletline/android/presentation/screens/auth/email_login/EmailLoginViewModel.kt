package com.walletline.android.presentation.screens.auth.email_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.model.auth.EmailValidationMessage
import com.walletline.domain.model.auth.RegisteredError
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.validator.ValidateUseCase
import com.walletline.domain.util.Resource
import com.walletline.presentation.screens.auth.email_login.EmailLoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class EmailLoginViewModel(
    private val authUseCase: AuthUseCase,
    private val validateUseCase: ValidateUseCase,
) : ViewModel(), EmailLoginContract {

    private val _state = MutableStateFlow(EmailLoginState())
    override val state: StateFlow<EmailLoginState> = _state.asStateFlow()

    private val effectChannel = Channel<EmailLoginContract.Effect>()
    override val effect: Flow<EmailLoginContract.Effect> = effectChannel.receiveAsFlow()

    override fun onEvent(event: EmailLoginContract.Event) {
        when (event) {
            is EmailLoginContract.Event.EmailChange -> _state.update { state -> state.copy(email = event.text) }
            EmailLoginContract.Event.OnContinueClicked -> handleAction { registerEmail() }
            EmailLoginContract.Event.OnEnterBySocialClicked -> handleAction {
                effectChannel.trySend(
                    EmailLoginContract.Effect.EnterBySocial
                )
            }
        }
    }

    private fun registerEmail() {
        val emailValidation = validateUseCase.validateEmail.execute(state.value.email)
        _state.update { state ->
            state.copy(
                emailError = emailValidation.message
            )
        }

        if (!emailValidation.isSuccess) return

        _state.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.register.execute(email = state.value.email)
                .let {
                    _state.update { state -> state.copy(isLoading = false) }
                    when (it) {
                        is Resource.Error -> handleRegisterError(it)
                        is Resource.Success -> {
                            effectChannel.send(
                                EmailLoginContract.Effect.RegisterSuccessful(
                                    otp = it.data.otp,
                                    email = state.value.email
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun handleRegisterError(it: Resource.Error<RegisteredError>) {
        when {
            !it.data?.emailError.isNullOrEmpty() -> {
                _state.update { state ->
                    state.copy(
                        emailError = EmailValidationMessage.Dynamic(it.data?.emailError?.first()!!)
                    )
                }
            }

            else -> effectChannel.trySend(
                EmailLoginContract.Effect.Error(
                    it.message ?: "Unknown error"
                )
            )
        }
    }

    private inline fun handleAction(action: () -> Unit) {
        _state.update { it.copy(isActionsEnabled = false) }
        action()
        _state.update { it.copy(isActionsEnabled = true) }
    }
}