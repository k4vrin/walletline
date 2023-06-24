package com.walletline.android.presentation.screens.auth.social_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.auth.SocialSignType
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.util.Resource
import com.walletline.presentation.screens.auth.social_login.SocialLoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SocialLoginViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel(), SocialLoginContract {

    private val _state = MutableStateFlow(SocialLoginState())
    override val state: StateFlow<SocialLoginState> = _state.asStateFlow()

    private val effectChannel = Channel<SocialLoginContract.Effect>()
    override val effect: Flow<SocialLoginContract.Effect> = effectChannel.receiveAsFlow()

    override fun onEvent(event: SocialLoginContract.Event) {
        when (event) {
            is SocialLoginContract.Event.SingInWithApple -> signInWithApple(SocialSignType.AppleAuth(event.uIdToken))
            is SocialLoginContract.Event.SingInWithFacebook -> signInWithFacebook(event.tokens)
            is SocialLoginContract.Event.SingInWithGoogle -> signInWithGoogle(event.tokens)
        }
    }

    private fun signInWithApple(appleAuth: SocialSignType.AppleAuth) {

    }

    private fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth) {

    }

    private fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val res = authUseCase.signInWithSocial.execute(type = googleAuth)
            _state.update { it.copy(isLoading = true) }
            when (res) {
                is Resource.Error -> effectChannel.send(
                    SocialLoginContract.Effect.ShowErrorMessage(
                        res.data ?: SocialSignInError.UnknownError
                    )
                )
                is Resource.Success -> effectChannel.send(SocialLoginContract.Effect.NavigateToPattern)
            }
        }
    }
}