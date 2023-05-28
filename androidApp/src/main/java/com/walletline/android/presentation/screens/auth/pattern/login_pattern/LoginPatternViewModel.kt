package com.walletline.android.presentation.screens.auth.pattern.login_pattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.presentation.screens.auth.pattern.login_pattern.LoginPatternState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginPatternViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel(), LoginPatternContract {

    private val _state = MutableStateFlow(LoginPatternState())
    override val state: StateFlow<LoginPatternState> = _state.asStateFlow()

    private val effectChannel = Channel<LoginPatternContract.Effect>()
    override val effect: Flow<LoginPatternContract.Effect> = effectChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            authUseCase.getPattern.execute().let {
                _state.update { mState ->
                    mState.copy(pattern = it)
                }
            }
        }
    }


    override fun onEvent(event: LoginPatternContract.Event) {
        when (event) {
            is LoginPatternContract.Event.DrawPatternFinished -> confirmPattern(event.pattern)
            LoginPatternContract.Event.DrawPatternStart -> updatePatternState()
        }
    }

    private fun updatePatternState() {
        _state.update { mState ->
            mState.copy(
                isScrollEnabled = false
            )
        }
    }

    private fun confirmPattern(pattern: String) {
        if (pattern == state.value.pattern) {
            effectChannel.trySend(LoginPatternContract.Effect.PatternIsVerified)
            return
        }
        _state.update { mState ->
            mState.copy(
                remainingAttempts = mState.remainingAttempts - 1
            )
        }

        if (state.value.remainingAttempts == 0) {
            effectChannel.trySend(LoginPatternContract.Effect.LockApp)
        }
    }

}