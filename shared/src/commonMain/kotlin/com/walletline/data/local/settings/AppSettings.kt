package com.walletline.data.local.settings

interface AppSettings {

    suspend fun setTrackingCode(trackCode: String)
    suspend fun getTrackCode(): String

    suspend fun setDevCode(devCode: String)
    suspend fun getDevCode(): String

    suspend fun setToken(token: String)
    suspend fun getToken(): String

    suspend fun setPattern(pattern: String)
    suspend fun getPattern(): String

    suspend fun setIsFingerprint(isFingerprint: Boolean)
    suspend fun getIsFingerprint(): Boolean

    suspend fun setIsOnBoarded(isOnBoarded: Boolean)
    suspend fun getIsOnBoarded(): Boolean
}