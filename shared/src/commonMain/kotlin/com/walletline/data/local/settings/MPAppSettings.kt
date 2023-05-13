package com.walletline.data.local.settings

import com.russhwolf.settings.coroutines.SuspendSettings

class MPAppSettings(
    private val settings: SuspendSettings
) : AppSettings {
    private object Keys {
        const val Token = "token"
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
}