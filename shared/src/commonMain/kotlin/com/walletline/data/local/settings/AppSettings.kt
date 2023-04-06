package com.walletline.data.local.settings

interface AppSettings {
    suspend fun setToken(token: String)
    suspend fun getToken(): String
}