package com.walletline.data.repository

import com.walletline.data.dto.request.RegisterReq
import com.walletline.data.dto.request.VerifyOtpReq
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.mapper.toDomain
import com.walletline.data.remote.firebase.auth.FirebaseAuthClient
import com.walletline.data.remote.server.AuthService
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.model.RegisteredSuccess
import com.walletline.domain.model.SignInResult
import com.walletline.domain.model.SocialSignType
import com.walletline.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val appSettings: AppSettings,
    private val firebaseAuthClient: FirebaseAuthClient,
) : AuthRepository {

    override suspend fun register(
        email: String,
        deviceName: String,
    ): ApiResponse<RegisteredSuccess, RegisteredError> {
        return authService.register(
            RegisterReq(email, deviceName)
        ).toDomain()
    }

    override suspend fun getOtp(devCode: String): ApiResponse<String, String> {
        return authService.getOtp(devCode).let {
            when (it) {
                ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
                is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(it.code, errorBody = null)
                is ApiResponse.Success -> ApiResponse.Success(body = it.body.otp)
                ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            }
        }
    }

    override suspend fun verifyOtp(otp: String, tracCode: String): ApiResponse<String, String> {
        return authService.verifyOtp(VerifyOtpReq(otp, tracCode)).let {
            when (it) {
                ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
                is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(
                    it.code,
                    errorBody = it.errorBody?.message
                )

                is ApiResponse.Success -> ApiResponse.Success(
                    body = it.body.accessToken ?: return ApiResponse.Error.NetworkError
                )

                ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            }
        }
    }

    override suspend fun setTrackingCode(trackCode: String) {
        appSettings.setTrackingCode(trackCode)
    }

    override suspend fun getTrackingCode(): String {
        return appSettings.getTrackCode()
    }

    override suspend fun setDevCode(devCode: String) {
        appSettings.setDevCode(devCode)
    }

    override suspend fun getDevCode(): String {
        return appSettings.getDevCode()
    }

    override suspend fun setToken(token: String) {
        appSettings.setToken(token)
    }

    override suspend fun getToken(): String {
        return appSettings.getToken()
    }

    override suspend fun signInWithGoogle(googleAuth: SocialSignType.GoogleAuth): SignInResult {
        return firebaseAuthClient.signInWithGoogle(googleAuth)
    }

    override suspend fun signInWithFacebook(facebookAuth: SocialSignType.FacebookAuth): SignInResult {
        return firebaseAuthClient.signInWithFacebook(facebookAuth)
    }

    override suspend fun getCurrentUserFirebaseID(): String? {
        return firebaseAuthClient.getCurrentUserIDToken()
    }

    override suspend fun setIsFingerFace(isFinger: Boolean) {
        appSettings.setIsFingerprint(true)
    }

    override suspend fun getIsFingerprint(): Boolean {
        return appSettings.getIsFingerprint()
    }

    override suspend fun setPattern(pattern: String) {
        appSettings.setPattern(pattern)
    }

    override suspend fun getPattern(): String {
        return appSettings.getPattern()
    }

    override suspend fun setOnBoarded(isOnBoarded: Boolean) {
        appSettings.setIsOnBoarded(isOnBoarded)
    }

    override suspend fun getOnBoarded(): Boolean {
        return appSettings.getIsOnBoarded()
    }
}