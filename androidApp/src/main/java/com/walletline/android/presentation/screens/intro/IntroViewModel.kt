package com.walletline.android.presentation.screens.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.presentation.screens.auth.admission.IntroState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class IntroViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel(), IntroContract {

    private val _state = MutableStateFlow(IntroState())
    override val state: StateFlow<IntroState> = _state.asStateFlow()

    private val effectChannel = Channel<IntroContract.Effect>()
    override val effect: Flow<IntroContract.Effect> = effectChannel.receiveAsFlow()

    init {
        getUserState()
    }

    override fun onEvent(event: IntroContract.Event) {
        when (event) {
            IntroContract.Event.UserOnBoarded -> handleUserOnBoard()
        }
    }


    private fun handleUserOnBoard() {
        viewModelScope.launch {
            authUseCase.setOnBoarded.execute(true)
                .let {
                    effectChannel.send(IntroContract.Effect.UserOnBoarded)
                }
        }
    }

    private fun getUserState() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.getAdmission.execute()
                .let {
                    effectChannel.send(IntroContract.Effect.Navigate(it))
                }
        }.invokeOnCompletion { _state.update { it.copy(isLoading = false) } }
    }

}