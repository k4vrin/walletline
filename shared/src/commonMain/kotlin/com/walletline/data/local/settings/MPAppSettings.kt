package com.walletline.data.local.settings

import com.russhwolf.settings.coroutines.SuspendSettings

class MPAppSettings(
    private val settings: SuspendSettings
) : AppSettings {
    private object Keys {
        const val Token = "token"
    }
    override suspend fun setToken(token: String) {
        settings.putString(key = Keys.Token, value = token)
    }

    override suspend fun getToken(): String {
        return settings.getString(key = Keys.Token, defaultValue = "")
    }
}