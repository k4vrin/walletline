package com.walletline.android.presentation.screens.auth.social_login

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.auth.SocialSignType
import com.walletline.presentation.screens.auth.social_login.SocialLoginState

interface SocialLoginContract :
    UnidirectionalViewModel<SocialLoginState, SocialLoginContract.Effect, SocialLoginContract.Event> {
    sealed interface Event {
        data class SingInWithGoogle(val tokens: SocialSignType.GoogleAuth) : Event
        data class SingInWithFacebook(val tokens: SocialSignType.FacebookAuth) : Event
        data class SingInWithApple(val uIdToken: String) : Event
    }

    sealed interface Effect {
        object NavigateToPattern: Effect
        object NavigateToEmailLogin: Effect
        data class ShowErrorMessage(val socialSignInError: SocialSignInError): Effect
    }
}