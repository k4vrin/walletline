package com.walletline.data.local.settings

import com.russhwolf.settings.coroutines.SuspendSettings

class MPAppSettings(
    private val settings: SuspendSettings
) : AppSettings {
    private object Keys {
        const val Token = "token"
        const val Pattern = "pattern"
        const val IsFingerPrint = "is_fingerprint"
        const val IsUserOnBoarded = "is_onboarded"
        const val TrackCode = "track_code"
        const val DevCode = "dev_code"
    }

    override suspend fun setTrackingCode(trackCode: String) {
        settings.putString(key = Keys.TrackCode, value = trackCode)
    }

    override suspend fun getTrackCode(): String {
        return settings.getString(key = Keys.TrackCode, defaultValue = "")
    }

    override suspend fun setDevCode(devCode: String) {
        settings.putString(key = Keys.DevCode, value = devCode)
    }

    override suspend fun getDevCode(): String {
        return settings.getString(key = Keys.DevCode, defaultValue = "")
    }

    override suspend fun setToken(token: String) {
        settings.putString(key = Keys.Token, value = token)
    }

    override suspend fun getToken(): String {
        return settings.getString(key = Keys.Token, defaultValue = "")
    }

    override suspend fun setPattern(pattern: String) {
        settings.putString(key = Keys.Pattern, value = pattern)

    }

    override suspend fun getPattern(): String {
        return settings.getString(key = Keys.Pattern, defaultValue = "")
    }


    override suspend fun setIsFingerprint(isFingerprint: Boolean) {
        settings.putBoolean(key = Keys.IsFingerPrint, value = isFingerprint)
    }

    override suspend fun getIsFingerprint(): Boolean {
        return settings.getBoolean(key = Keys.IsFingerPrint, defaultValue = false)
    }

    override suspend fun setIsOnBoarded(isOnBoarded: Boolean) {
        settings.putBoolean(key = Keys.IsUserOnBoarded, value = isOnBoarded)
    }

    override suspend fun getIsOnBoarded(): Boolean {
        return settings.getBoolean(key = Keys.IsUserOnBoarded, defaultValue = false)
    }


}