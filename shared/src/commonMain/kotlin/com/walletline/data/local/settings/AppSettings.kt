package com.walletline.data.local.settings

interface AppSettings {

    suspend fun setTrackingCode(trackCode: String)
    suspend fun getTrackCode(): String

    suspend fun setDevCode(devCode: String)
    suspend fun getDevCode(): String
    suspend fun setToken(token: String)
    suspend fun getToken(): String
}