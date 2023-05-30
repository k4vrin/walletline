package com.walletline.domain.use_case.auth

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.SocialSignInError
import com.walletline.domain.model.SocialSignType
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.withContext

class SignInWithSocial(
    private val repository: AuthRepository,
    private val dispatchers: CoroutineDispatchers
) {
    @NativeCoroutines
    suspend fun execute(type: SocialSignType): Resource<Boolean, SocialSignInError> {
        return when (type) {
            is SocialSignType.GoogleAuth -> handeGoogleSignIn(type)
            is SocialSignType.AppleAuth -> handleAppleSignIn(type)
            is SocialSignType.FacebookAuth -> handleFacebookSignIn(type)
        }
    }

    private suspend fun handleFacebookSignIn(facebookAuth: SocialSignType.FacebookAuth): Resource<Boolean, SocialSignInError> {
        val firebaseResult = withContext(dispatchers.network) { repository.signInWithFacebook(facebookAuth) }

        if (firebaseResult.data == null) {
            return when (firebaseResult.errorMessage) {
                null -> Resource.Error(SocialSignInError.UnknownError)
                else -> Resource.Error(firebaseResult.errorMessage)
            }
        }

        Logger.d(tag = "SignInWithSocial") { firebaseResult.data.toString() }

        return Resource.Success(true)
    }

    private suspend fun handleAppleSignIn(appleAuth: SocialSignType.AppleAuth): Resource<Boolean, SocialSignInError> {
        return Resource.Error(SocialSignInError.Collision)
    }

    private suspend fun handeGoogleSignIn(googleAuth: SocialSignType.GoogleAuth): Resource<Boolean, SocialSignInError> {

        val firebaseResult = withContext(dispatchers.network) { repository.signInWithGoogle(googleAuth) }

        if (firebaseResult.data == null) {
            return when (firebaseResult.errorMessage) {
                null -> Resource.Error(SocialSignInError.UnknownError)
                else -> Resource.Error(firebaseResult.errorMessage)
            }
        }

        val verifyIdToken = withContext(dispatchers.network) { repository.getCurrentUserFirebaseID() }

        Logger.d(tag = "SignInWithSocial") {
            "auth result: ${firebaseResult.data}, verifyIdToken: $verifyIdToken"
        }

        return Resource.Success(true)
    }
}