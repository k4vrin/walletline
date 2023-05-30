package com.walletline.android.presentation.social_auth.apple_auth

import android.app.Activity
import android.content.Context
import co.touchlab.kermit.Logger
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.walletline.domain.model.SocialSignType
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class AppleAuthUiClient {

    private val auth = Firebase.auth

    suspend fun signIn(context: Context): SocialSignType.AppleAuth? {
        val provider = OAuthProvider.newBuilder("apple.com")
        provider.scopes = listOf("email", "name")

        // Checking for pending signIn first
        val pending = auth.pendingAuthResult
        try {
            pending?.await()?.user?.let { firebaseUser ->
                Logger.d(tag = "AppleAuthUiClient") { "Pending User: name:${firebaseUser.displayName} email:${firebaseUser.email}" }
                firebaseUser.getIdToken(/* forceRefresh = */ false).await()?.token?.let { verifyToken ->
                    return SocialSignType.AppleAuth(verifyToken = verifyToken)
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Logger.d(tag = "AppleAuthUiClient") { e.localizedMessage ?: "Pending auth failure" }
        }

        // if there is no pending sign in
        try {
            val authResult = auth.startActivityForSignInWithProvider(context as Activity, provider.build()).await()
            val token = authResult?.user?.getIdToken(false)?.await()?.token
            token?.let { verifyToken ->
                return SocialSignType.AppleAuth(verifyToken = verifyToken)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Logger.d(tag = "AppleAuthUiClient") { e.localizedMessage ?: "Auth failure" }
        }

        return null
    }


    fun signOut() {
        auth.signOut()
    }

}