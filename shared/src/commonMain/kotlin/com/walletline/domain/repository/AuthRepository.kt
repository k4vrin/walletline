package com.walletline.domain.repository

import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.model.SignInResult
import com.walletline.domain.model.SocialSignType

interface AuthRepository {
    suspend fun register(email: String, deviceName: String): ApiResponse<RegisteredSuccess, RegisteredError>
    suspend fun getOtp(devCode: String): ApiResponse<String, String>
    suspend fun verifyOtp(otp: String, tracCode: String): ApiResponse<String, String>
    suspend fun setTrackingCode(trackCode: String)
    suspend fun getTrackingCode(): String
    suspend fun setDevCode(devCode: String)
    suspend fun getDevCode(): String
    suspend fun setToken(token: String)
    suspend fun getToken(): String
    suspend fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth): SignInResult
    suspend fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth): SignInResult
    suspend fun getCurrentUserFirebaseID(): String?
    suspend fun setIsFingerFace(isFinger: Boolean)
    suspend fun getIsFingerprint(): Boolean
    suspend fun setPattern(pattern: String)
    suspend fun getPattern(): String
    suspend fun setOnBoarded(isOnBoarded: Boolean)
    suspend fun getOnBoarded(): Boolean
}