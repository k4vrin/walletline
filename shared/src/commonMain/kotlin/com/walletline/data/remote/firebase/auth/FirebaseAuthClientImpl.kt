package com.walletline.data.remote.firebase.auth

import com.walletline.domain.model.auth.SignInResult
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.auth.SocialSignType
import com.walletline.domain.model.auth.UserData
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FacebookAuthProvider
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlin.coroutines.cancellation.CancellationException

class FirebaseAuthClientImpl : FirebaseAuthClient {

    private val auth = Firebase.auth


    override suspend fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth): SignInResult {
        val credential = GoogleAuthProvider.credential(googleAuth.idToken, googleAuth.accessToken)
        return signInWithCredential(credential)
    }

    override suspend fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth): SignInResult {
        val credential = FacebookAuthProvider.credential(facebookAuth.accessToken)
        return signInWithCredential(credential)
    }

    private suspend fun signInWithCredential(credential: AuthCredential): SignInResult {
        return try {
            val user = auth.signInWithCredential(credential).user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePicUrl = photoURL
                    )
                },
                errorMessage = null
            )
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            e.printStackTrace()
            SignInResult(data = null, errorMessage = SocialSignInError.InvalidCredentials)
        } catch (e: FirebaseAuthInvalidUserException) {
            e.printStackTrace()
            SignInResult(data = null, errorMessage = SocialSignInError.InvalidUser)
        } catch (e: FirebaseAuthUserCollisionException) {
            e.printStackTrace()
            SignInResult(data = null, errorMessage = SocialSignInError.Collision)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(data = null, errorMessage = SocialSignInError.UnknownError)
        }
    }

    override suspend fun signOut() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    override fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePicUrl = photoURL.toString()
        )
    }

    override suspend fun getCurrentUserIDToken(): String? {
        return try {
            auth.currentUser?.getIdToken(true)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
    }


}