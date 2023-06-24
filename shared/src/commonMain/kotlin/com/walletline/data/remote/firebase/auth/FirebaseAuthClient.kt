package com.walletline.data.remote.firebase.auth

import com.walletline.domain.model.auth.SignInResult
import com.walletline.domain.model.auth.SocialSignType
import com.walletline.domain.model.auth.UserData

interface FirebaseAuthClient {

    suspend fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth): SignInResult

    suspend fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth): SignInResult

    suspend fun signOut()

    fun getSignedInUser(): UserData?

    suspend fun getCurrentUserIDToken(): String?
}