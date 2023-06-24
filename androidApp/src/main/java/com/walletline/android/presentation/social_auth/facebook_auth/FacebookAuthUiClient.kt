package com.walletline.android.presentation.social_auth.facebook_auth

import android.content.Context
import androidx.activity.result.ActivityResultRegistryOwner
import co.touchlab.kermit.Logger
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.walletline.domain.model.auth.SocialSignType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class FacebookAuthUiClient {

    suspend fun signIn(context: Context): SocialSignType.FacebookAuth = suspendCancellableCoroutine { cont ->
        if (context !is ActivityResultRegistryOwner) {
            Logger.d(tag = "FacebookAuthUiClient") { "Login failed: context !is ActivityResultRegistryOwner" }
            cont.resumeWithException(CancellationException("context !is ActivityResultRegistryOwner"))
        }
        val callbackManager = CallbackManager.Factory.create()
        val loginManger = LoginManager.getInstance()
        loginManger.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Logger.d(tag = "FacebookAuthUiClient") { "Login Cancelled" }
                    cont.resumeWithException(CancellationException("Login Cancelled"))
                }

                override fun onError(error: FacebookException) {
                    Logger.d(tag = "FacebookAuthUiClient") { "Error Happen: ${error.message}" }
                    cont.resumeWithException(error)
                }

                override fun onSuccess(result: LoginResult) {
                    Logger.d(tag = "FacebookAuthUiClient") { "Login Success: $result" }
                    cont.resumeWith(result = Result.success(SocialSignType.FacebookAuth(accessToken = result.accessToken.token)))
                }
            }
        )
        loginManger.logIn(context as ActivityResultRegistryOwner, callbackManager, listOf("email", "public_profile"))
    }


    fun signOut() {
        LoginManager.getInstance().logOut()
    }
}