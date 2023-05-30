package com.walletline.data.remote.firebase.auth

import com.walletline.domain.model.SignInResult
import com.walletline.domain.model.SocialSignType
import com.walletline.domain.model.UserData

interface FirebaseAuthClient {

    suspend fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth): SignInResult

    suspend fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth): SignInResult

    suspend fun signOut()

    fun getSignedInUser(): UserData?

    suspend fun getCurrentUserIDToken(): String?
}